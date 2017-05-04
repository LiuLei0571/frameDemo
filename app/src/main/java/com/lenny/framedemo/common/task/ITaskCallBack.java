package com.lenny.framedemo.common.task;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public interface ITaskCallBack<T> {

    void onBeforeCall();

    void onAfterCall();

    void onComplete(T result);

    void onException(Throwable throwable);

    void onCancelled();
}
