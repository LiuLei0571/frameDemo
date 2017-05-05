package com.lenny.framedemo.compent.web.protocol;

import android.content.Intent;

import com.lenny.framedemo.compent.base.IAct;
import com.lenny.framedemo.compent.web.callback.ICallBack;


/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public interface IProtocol<P> {
    void doExecute(final IAct iAct, final ICallBack iCallBackm, P params);

    void onActivityResult(final IAct iAct, final ICallBack iCallBack, int requestCode, int resultCode, Intent data);

    int[] useCode();
}
