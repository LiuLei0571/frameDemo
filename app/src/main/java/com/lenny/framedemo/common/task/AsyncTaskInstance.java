package com.lenny.framedemo.common.task;

import android.support.annotation.NonNull;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/4.
 * 邮箱：liulei2@aixuedai.com
 */


public class AsyncTaskInstance<Result> extends AbstractTaskInstance<Result> {
    public AsyncTaskInstance(ITaskBackGround<Result> callable, ITaskCallBack<Result> callBack) {
        super(callable, callBack);
    }

    public AsyncTaskInstance(@NonNull Runnable runnable) {
        super(runnable);
    }

    public AsyncTaskInstance<Result> taskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    public AsyncTaskInstance<Result> groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public AsyncTaskInstance seriaExecute(boolean isSerial) {
        this.serialExecute = isSerial;
        return this;
    }

    /**
     * 优先级
     *
     * @param priority
     * @return
     */
    public AsyncTaskInstance<Result> priority(int priority) {
        this.priority = priority;
        return this;
    }

    /**
     * 去重策略
     * {@link #DISCARD_NEW}
     * {@link #CANCEL_OLD}
     * {@link #FORCE_SUBMIT}
     *
     * @param dualPolicy
     * @return
     */
    public AsyncTaskInstance<Result> dualPolicy(int dualPolicy) {
        this.dualPolicy = dualPolicy;
        return this;
    }

    public static AsyncTaskInstance<Void> build(Runnable runnable) {
        return new AsyncTaskInstance<>(runnable);
    }

    public static <Result> AsyncTaskInstance<Result> build(ITaskBackGround<Result> callable, ITaskCallBack<Result> callback) {

        return new AsyncTaskInstance<>(callable, callback);
    }
}
