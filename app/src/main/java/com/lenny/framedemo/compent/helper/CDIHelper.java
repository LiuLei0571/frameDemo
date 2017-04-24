package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.event.IEvent;
import com.lenny.framedemo.common.http.impl.okhttp3.CookiesManager;
import com.lenny.framedemo.common.image.ImageDisplayLoader;
import com.lenny.framedemo.common.parse.IParse;
import com.lenny.framedemo.compent.cdi.CDI;
import com.lenny.framedemo.compent.ui.AppToast;

import javax.inject.Inject;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public final class CDIHelper {
    @Inject
    IParse mIParse;
    @Inject
    IEvent mIEvent;
    @Inject
    AppToast mAppToast;
    @Inject
    ImageDisplayLoader mImageDisplayLoader;
    @Inject
    public CookiesManager mCookiesManager;
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
