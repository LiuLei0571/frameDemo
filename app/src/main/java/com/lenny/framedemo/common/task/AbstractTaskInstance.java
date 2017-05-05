package com.lenny.framedemo.common.task;

import android.support.annotation.NonNull;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.common.thread.ThreadLocalHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public class AbstractTaskInstance<Result> extends FutureTask<Result> implements ITaskInstance, IGroupedTaskInstance, IPriorityTask {
    protected String taskName = DEFAULT_TASK_NAME;
    protected String groupName = DEFAULT_GROUP_NAME;
    protected ITaskCallBack<Result> callBack;
    protected boolean serialExecute;
    protected int dualPolicy = DISCARD_NEW;
    protected int priority = PRIOR_NOMAL;
    protected int status = STATUS_NEW;


    public AbstractTaskInstance(final ITaskBackGround<Result> callable, final ITaskCallBack<Result> callBack) {
        super(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return callable.onBackGround();
            }
        });
        this.callBack = callBack;

    }

    public AbstractTaskInstance(@NonNull Runnable runnable) {
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
        try {
            callBack.onAfterCall();

        } catch (Exception e) {

        }

    }

    public void onComplete() {
        try {
            Result result = get();
            if (result != null) {
                callBack.onComplete(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void onException(Throwable throwable) {

    }

    public void onCancelled() {
        try {
            callBack.onCancelled();

        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        if (callBack != null) {
            callBack.onBeforeCall();
        }
        ThreadLocalHelper.setInfoThreadLocal(groupName(), taskName());
        try {
            super.run();

        }
        catch (Exception e){

        }
    }

    @Override
    protected void done() {
        if (callBack != null) {
            ThreadHelper.postMain(new Runnable() {
                @Override
                public void run() {
                    onAfterCall();
                    if (isCancelled()) {
                        onCancelled();
                    } else {
                        onComplete();
                    }
                }
            });
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        AbstractTaskInstance that = (AbstractTaskInstance) obj;

        return taskName.equals(that.taskName) && groupName.equals(that.groupName);
    }

    @Override
    public String groupName() {
        return groupName;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String taskName() {
        return taskName;
    }

    @Override
    public boolean serialExecute() {
        return serialExecute;
    }

    @Override
    public int dualPolicy() {
        return dualPolicy;
    }

    @Override
    public int compare(IPriorityTask o1, IPriorityTask o2) {
        return 0;
    }
}
