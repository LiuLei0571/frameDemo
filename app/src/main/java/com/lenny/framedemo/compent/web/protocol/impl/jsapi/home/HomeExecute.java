package com.lenny.framedemo.compent.web.protocol.impl.jsapi.home;

import android.content.Intent;

import com.lenny.framedemo.compent.base.IAct;
import com.lenny.framedemo.compent.web.callback.ICallBack;
import com.lenny.framedemo.compent.web.protocol.BaseProtocolInstance;
import com.lenny.framedemo.project.home.activity.MainActivity;


/**
 * 用途：
 * Created by milk on 17/3/17.
 * 邮箱：649444395@qq.com
 */

public class HomeExecute extends BaseProtocolInstance {
    @Override
    public void doExecute(IAct iAct, ICallBack iCallBack, Object params) {
        super.doExecute(iAct, iCallBack, params);
        iAct.startActivity(new Intent(iAct.getActivity(),MainActivity.class));

    }
}
