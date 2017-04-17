package com.lenny.framedemo.compent.cdi;

import android.content.Context;

import com.lenny.framedemo.compent.base.BaseActivity;
import com.lenny.framedemo.compent.cdi.cmp.ActivityComponent;
import com.lenny.framedemo.compent.cdi.cmp.ActivityModule;
import com.lenny.framedemo.compent.cdi.cmp.AppComponent;
import com.lenny.framedemo.compent.cdi.cmp.AppModule;
import com.lenny.framedemo.compent.cdi.cmp.ManagerModule;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public class CDI {
    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static void init(Context context) {
        AppModule appModule = new AppModule(context);
        ManagerModule managerModule = new ManagerModule();
    }

    public static ActivityComponent createActivityComponent(BaseActivity activity) {
        ActivityComponent activityComponent = sAppComponent.plus(new ActivityModule(activity));
        return activityComponent;
    }
}
