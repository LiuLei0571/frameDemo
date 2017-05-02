package com.lenny.framedemo.project.home.manager;

import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.compent.base.BaseManager;
import com.lenny.framedemo.compent.constants.Apis;


/**
 * 用途：
 * 作者：Created by liulei on 17/5/2.
 * 邮箱：liulei2@aixuedai.com
 */


public class HomeManager extends BaseManager {
    public IResult<Object> home(){
        return super.execute(Apis.home);
    }
}
