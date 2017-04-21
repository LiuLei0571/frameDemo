package com.lenny.framedemo.common.http;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public interface ICall {
    int SATUS_NEW = 0;
    int STATUE_READY = 1;
    int STATUS_RUNNING = 3;
    int STATUS_OVER = 4;

    int getStatus();

    IResponse execute() throws Exception;

    IRequest getRequest();
}
