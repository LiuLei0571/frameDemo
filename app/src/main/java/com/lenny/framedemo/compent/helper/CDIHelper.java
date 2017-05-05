package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.event.IEvent;
import com.lenny.framedemo.common.http.HttpScheduler;
import com.lenny.framedemo.common.http.impl.okhttp3.CookiesManager;
import com.lenny.framedemo.common.image.ImageDisplayLoader;
import com.lenny.framedemo.common.parse.IParse;
import com.lenny.framedemo.common.task.TaskScheduler;
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
    public IParse mIParse;
    @Inject
    public IEvent mIEvent;
    @Inject
    public AppToast mAppToast;
    @Inject
    public ImageDisplayLoader mImageDisplayLoader;
    @Inject
    public TaskScheduler mTaskScheduler;
    @Inject
    public CookiesManager mCookiesManager;
    @Inject
    public HttpScheduler mHttpScheduler;
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
