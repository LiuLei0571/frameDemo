package com.lenny.framedemo.common.http;

import com.lenny.framedemo.compent.http.ErrorInfo;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public interface IResult<T extends Object> {

    T data();

    boolean success();

    String msg();

    ErrorInfo errorInfo();
}
