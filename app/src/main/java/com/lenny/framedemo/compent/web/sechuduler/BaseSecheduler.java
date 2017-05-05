package com.lenny.framedemo.compent.web.sechuduler;

import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.compent.web.ExecuteFactory;
import com.lenny.framedemo.compent.web.bean.ProtocolBean;
import com.lenny.framedemo.compent.web.callback.ICallBack;
import com.lenny.framedemo.compent.web.parser.IParamParser;
import com.lenny.framedemo.compent.web.protocol.IProtocol;
import com.lenny.framedemo.compent.web.protocol.param.UriBean;
import com.lenny.framedemo.compent.web.utils.WebUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/1/18.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseSecheduler<CallBack> {
    private Map<String, ProtocolBean> hybrids;
    private SparseArray<ICallBack> callBacks;
    private ExecuteFactory instanceFactory;
    private IParamParser mIParamParser;

    public BaseSecheduler(ProtocolBean[] executes, IParamParser iParamParser) {
        instanceFactory = ExecuteFactory.getInstance();
        callBacks = new SparseArray<>();
        hybrids = new HashMap<>();
        loadExecutes(executes);
        this.mIParamParser = iParamParser;
    }

    public void loadExecutes(ProtocolBean[] protocols) {
        if (protocols != null) {
            StringBuilder stringBuilder;
            for (ProtocolBean protocol : protocols) {
                stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(protocol.getModule())) {
                    stringBuilder.append(protocol.getModule());
                }
                stringBuilder.append(protocol.getMethod());
                hybrids.put(stringBuilder.toString(), protocol);
            }
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean acted = false;
        IProtocol protocol = instanceFactory.findProtoclByCode(requestCode);
        ICallBack callBack = callBacks.get(requestCode);
        if (protocol != null && callBack != null) {
            try {
                doActivityResult(protocol, callBack, requestCode, resultCode, data);
                acted = true;
            } catch (Exception e) {

            }
        }
        return acted;
    }

    public void doExecute(UriBean uriBean, CallBack callBackStr) {
        String module = uriBean.getModule();
        String method = uriBean.getMethod();
        String params = uriBean.getParams();
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(module)) {
            stringBuilder.append(module);
        }
        stringBuilder.append(method);
        ProtocolBean protocolBean = hybrids.get(stringBuilder.toString());
        if (protocolBean != null) {
            Object paramObj = null;
            if (this.mIParamParser != null) {
                Type type = WebUtil.getSuperClassParam(protocolBean.getExecuteCls());
                paramObj = mIParamParser.getParam(type, params);
            }
            final IProtocol instance=ExecuteFactory.getInstance().getExexuteInstance(protocolBean);
            final ICallBack iCallBack=initCallBack(callBackStr,protocolBean.getDefaultCallback());
            int[] codes=instance.useCode();
            if (codes != null) {
                for (int code:codes) {
                    callBacks.put(code,iCallBack);
                }
            }
            if (protocolBean.isPostMain() && !ThreadHelper.isMainThread()) {
                final Object finalObj=paramObj;
                ThreadHelper.postMain(new Runnable() {
                    @Override
                    public void run() {
                        doAction(instance,iCallBack,finalObj);
                    }
                });
            }else {
                doAction(instance,iCallBack,paramObj);
            }
        }else {
        }

    }

    protected abstract void doAction(IProtocol intance, ICallBack callBack, Object paramObj);

    protected abstract void doActivityResult(IProtocol iActivityResult, ICallBack callBack, int requestCode, int resultCode, Intent data);

    protected abstract ICallBack initCallBack(CallBack callback, String defaultCallBack);

    protected void onDestory() {

    }
}
