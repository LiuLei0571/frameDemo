package com.lenny.framedemo.compent.cdi.cmp;

import com.lenny.framedemo.compent.helper.CDIHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@Singleton
@Component(modules = {ManagerModule.class, AppModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule activityModule);
    void plus(CDIHelper instance);
}
