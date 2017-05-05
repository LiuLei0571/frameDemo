package com.lenny.framedemo.compent.web;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.lenny.framedemo.compent.web.bean.ProtocolBean;
import com.lenny.framedemo.compent.web.parser.impl.UrlJsonParamParse;
import com.lenny.framedemo.compent.web.protocol.param.UriBean;
import com.lenny.framedemo.compent.web.sechuduler.impl.WebSecheduler;
import com.lenny.framedemo.compent.web.utils.WebUtil;
import com.lenny.framedemo.compent.web.view.widget.CommonWebViewClient;


/**
 * 用途：
 * Created by milk on 17/1/20.
 * 邮箱：649444395@qq.com
 */

public class MyUrlIntenterceper implements CommonWebViewClient.UrlIntercepter {
    private WebSecheduler hybridSechudler;

    public MyUrlIntenterceper(WebInterface webInterface, ProtocolBean[] executes) {
        hybridSechudler = new WebSecheduler(webInterface, executes, UrlJsonParamParse.getInstacne());
    }

    @Override
    public boolean doIntercept(FragmentActivity activity, String url) {
        if (url != null) {
            UriBean uriBean = WebUtil.buildUriBean(url);
            if (uriBean != null) {
                hybridSechudler.doExecute(uriBean, uriBean.getCallback());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return hybridSechudler.onActivityResult(requestCode, resultCode, data);
    }
}
