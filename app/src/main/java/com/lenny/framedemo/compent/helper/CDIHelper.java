package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.compent.cdi.CDI;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public final class CDIHelper {
    private static CDIHelper instance = null;

    public CDIHelper() {
        CDI.getAppComponent().plus(this);
    }

    public static synchronized CDIHelper getInstance() {
        if (instance == null) {
            instance = new CDIHelper();
        }
        return instance;
    }
}
