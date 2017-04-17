package com.lenny.framedemo.compent.sys.impl;

import android.content.Context;

import com.lenny.framedemo.common.helper.ContextHelper;
import com.lenny.framedemo.compent.constants.Configs;
import com.lenny.framedemo.compent.sys.IAppinits;

import icepick.Icepick;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public class FrameWorkInit implements IAppinits {
    @Override
    public void init(Context appContext) {
        Icepick.setDebug(Configs.LOG_D);
        ContextHelper.setContext(appContext);
    }
}
