package com.lenny.framedemo.compent.cdi.cmp;

import com.lenny.framedemo.project.home.manager.HomeManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@Module
public class ManagerModule {
    @Provides
    @Singleton
    protected HomeManager provideManager() {
        return new HomeManager();
    }
}
