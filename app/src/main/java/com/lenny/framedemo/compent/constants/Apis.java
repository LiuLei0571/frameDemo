package com.lenny.framedemo.compent.constants;

import com.lenny.framedemo.compent.http.Api;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/2.
 * 邮箱：liulei2@aixuedai.com
 */


public interface Apis {
    Api home = Api.Post("banner/homePage.htm", Types.home).setLogin(false);
}
