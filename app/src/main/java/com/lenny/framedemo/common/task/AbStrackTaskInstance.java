package com.lenny.framedemo.common.task;

import android.support.annotation.NonNull;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.common.thread.ThreadLocalHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public class AbStrackTaskInstance<Result> extends FutureTask<Result> implements ITaskInstance, IGroupTaskInstance, IPriorityTask {
    protected String taskName = DEFAULT_TASK_NAME;
    protected String groupName = DEFAULT_GROUP_NAME;
    protected ITaskCallBack<Result> callBack;
    protected boolean serialExecute;
    protected int dualPolicy = DISCARD_NEW;
    protected int priority = PRIOR_NOMAL;
    protected int status = STATUS_NEW;
    /**
     * 任务提交的时间
     */
    private long submitTime;
    /**
     * 开始执行时间
     */
    private long beginExceute;
    /**
     * 结束执行时间
     */
    private long endExceute;


    public AbStrackTaskInstance(final ITaskBackGround<Result> callable, final ITaskCallBack<Result> callBack) {
        super(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return callable.onBackGround();
            }
        });
        this.callBack = callBack;

    }

    public AbStrackTaskInstance(@NonNull Runnable runnable) {
        super(runnable, null);
    }


    public void onBeforeCall() {
        ThreadHelper.postMain(new Runnable() {
            @Override
            public void run() {
                callBack.onBeforeCall();
            }
        });

    }

    public void onAfterCall() {

    }

    public void onComplete() {

    }

    public void onException(Throwable throwable) {

    }

    public void onCancelled() {

    }

    @Override
    public void run() {
        if (callBack != null) {
            callBack.onBeforeCall();
        }
        ThreadLocalHelper.setInfoThreadLocal(groupName(), taskName());
        super.run();
    }

    @Override
    protected void done() {
        super.done();
    }

    @Override
    public String groupName() {
        return groupName;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int setStatus(int status) {
        return 0;
    }

    @Override
    public String taskName() {
        return taskName;
    }

    @Override
    public boolean serialExecute() {
        return false;
    }

    @Override
    public int dualPolicy() {
        return 0;
    }

    @Override
    public int compare(IPriorityTask o1, IPriorityTask o2) {
        return 0;
    }
}
