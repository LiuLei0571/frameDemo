package com.lenny.framedemo.common.http;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public interface IRequestListener {
    void beforRequest(ICall iCall);

    void afterRequest(ICall call, IResponse response);
}
