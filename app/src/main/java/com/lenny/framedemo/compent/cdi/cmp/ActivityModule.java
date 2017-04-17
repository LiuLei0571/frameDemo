package com.lenny.framedemo.compent.cdi.cmp;

import android.content.Context;

import com.lenny.framedemo.common.task.IGroup;
import com.lenny.framedemo.compent.base.BaseActivity;
import com.lenny.framedemo.compent.base.IView;
import com.lenny.framedemo.compent.cdi.annotation.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity baseActivity) {
        activity = baseActivity;
    }

    @Provides
    @ActivityScope
    BaseActivity provideBaseActivity() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    IGroup provideGroup() {
        final String groupName = activity.groupName();

        return new IGroup() {
            @Override
            public String groupName() {
                return groupName;
            }
        };
    }

    @Provides
    @ActivityScope
    IView provideIView() {
        return activity;
    }
}
