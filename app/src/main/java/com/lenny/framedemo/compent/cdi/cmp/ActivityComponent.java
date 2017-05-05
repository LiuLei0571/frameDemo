package com.lenny.framedemo.compent.cdi.cmp;

import com.lenny.framedemo.compent.cdi.annotation.ActivityScope;
import com.lenny.framedemo.project.home.activity.MainActivity;

import dagger.Subcomponent;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void plus(MainActivity mainActivity);
}
