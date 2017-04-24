package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.http.HttpScheduler;
import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.compent.http.Api;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class HttpHelper {
    private static HttpScheduler sHttpScheduler;

    static {
        sHttpScheduler = CDIHelper.getInstance().mHttpScheduler;
    }

    public static <T> IResult<T> execute(Api api, Object params) {
        return null;
    }
}
