package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.task.AsyncTaskInstance;
import com.lenny.framedemo.common.task.ITaskBackGround;
import com.lenny.framedemo.common.task.ITaskCallBack;
import com.lenny.framedemo.common.task.TaskScheduler;
import com.lenny.framedemo.compent.thread.ITask;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/4.
 * 邮箱：liulei2@aixuedai.com
 */


public class TaskHelper {
    private static TaskScheduler taskScheduler;

    static {
        taskScheduler = CDIHelper.getInstance().mTaskScheduler;
    }

    public static void cancelGroup(String groupName) {
        taskScheduler.cancleGroup(groupName);
    }

    public static void cancelTask(String groupName, String taskName) {
        taskScheduler.cancleTask(groupName, taskName);
    }
    public static <T> AsyncTaskInstance<T> submitTask(String taskName, String groupName, ITask<T> task) {
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(task, task)
                .taskName(taskName)
                .groupName(groupName)
                .seriaExecute(false);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }

    public static <T> AsyncTaskInstance<T> submitTask(String taskName, String groupName, ITaskBackGround<T> task, ITaskCallBack<T> callBack) {
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(task, callBack)
                .taskName(taskName)
                .groupName(groupName)
                .seriaExecute(false);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }
}
