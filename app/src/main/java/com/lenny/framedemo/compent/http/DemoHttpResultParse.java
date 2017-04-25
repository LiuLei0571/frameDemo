package com.lenny.framedemo.compent.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lenny.framedemo.common.http.HttpResultParse;
import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.ICall;
import com.lenny.framedemo.common.http.IResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class DemoHttpResultParse implements HttpResultParse {
    @Override
    public IResult parseResult(String json, IApi iapi) {
        long startTime = System.currentTimeMillis();
        Type type = iapi.getResultType();
        Result result = null;
        if (iapi instanceof Api) {
            Api api = (Api) iapi;
            IHost host = api.getHost();
            result = parseResultCommon(json, type);
        }
        return result;
    }

    public Result parseResultCommon(String json, Type type) {
        if (TextUtils.isEmpty(json)) {
            return Result.fail("网络异常！");
        }
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            if (jsonObject != null) {
                Object body = jsonObject.getObject("data", Object.class);
                ErrorInfo errorInfo = jsonObject.getObject("meta", ErrorInfo.class);
                if (body != null && type != null) {
                    if (type == String.class || type == List.class) {
                        body = body.toString();
                    } else {
                        body = JSON.parseObject(body.toString(), type);
                    }
                }
                return new Result<>(body, errorInfo);
            }
        } catch (Exception e) {
            return Result.fail("网络异常！");

        }
        return null;
    }

    @Override
    public IResult onException(ICall iCall, Exception e) {
        if (e instanceof IOException) {
            IApi api = iCall.getRequest().getAPi();
            return Result.fail("网络异常！");
        }
        return null;
    }
}
