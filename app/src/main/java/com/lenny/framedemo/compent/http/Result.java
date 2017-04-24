package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.IResult;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class Result implements IResult {
    @Override
    public String code() {
        return null;
    }

    @Override
    public Object data() {
        return null;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public String json() {
        return null;
    }
}
