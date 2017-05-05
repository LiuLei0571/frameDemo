package com.lenny.framedemo.common.task;

/**
 * 用途：任务状态
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public interface ITaskInstance extends Runnable {
    int STATUS_NEW = 0;
    int STATUS_WAIT = 1;
    int STATUS_READY = 2;
    int STATUS_RUNNING = 3;
    int STATUS_OVER = 4;
    int STATUS_CANCEL = 5;

    int getStatus();

    void setStatus(int status);
}
