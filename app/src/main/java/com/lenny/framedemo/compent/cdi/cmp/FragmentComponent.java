package com.lenny.framedemo.compent.cdi.cmp;

import com.lenny.framedemo.compent.cdi.annotation.FragmentScope;

import dagger.Subcomponent;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@FragmentScope
@Subcomponent(modules = {FragmentModule.class})
 public interface FragmentComponent {
}
