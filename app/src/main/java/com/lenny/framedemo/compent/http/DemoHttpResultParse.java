package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.HttpResultParse;
import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.ICall;
import com.lenny.framedemo.common.http.IResult;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class DemoHttpResultParse implements HttpResultParse {
    @Override
    public IResult parseResult(String json, IApi iapi) {
        long startTime=System.currentTimeMillis();
        Type type=iapi.getResultType();
        Result result=null;
        if (iapi instanceof Api) {
            Api api= (Api) iapi;
            IHost host=api.getHost();
            result =parseResultCommon(json,type);
        }
        return result;
    }

    public Result parseResultCommon(String json, Type type) {

        return null;
    }

    @Override
    public IResult onException(ICall iCall, Exception e) {
        return null;
    }
}
