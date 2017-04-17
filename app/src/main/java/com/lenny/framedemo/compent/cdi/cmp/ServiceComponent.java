package com.lenny.framedemo.compent.cdi.cmp;

import com.lenny.framedemo.compent.cdi.annotation.ServiceScope;

import dagger.Subcomponent;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@ServiceScope
@Subcomponent(modules = {ServiceModule.class})
public interface ServiceComponent {
}
