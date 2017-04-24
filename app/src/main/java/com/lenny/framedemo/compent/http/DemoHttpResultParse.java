package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.HttpResultParse;
import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.ICall;
import com.lenny.framedemo.common.http.IResult;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class DemoHttpResultParse implements HttpResultParse {
    @Override
    public IResult parseResult(String json, IApi api) {
        return null;
    }

    @Override
    public IResult onException(ICall iCall, Exception e) {
        return null;
    }
}
