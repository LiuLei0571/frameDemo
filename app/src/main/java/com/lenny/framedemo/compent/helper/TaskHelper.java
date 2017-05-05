package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.common.task.AsyncTaskInstance;
import com.lenny.framedemo.common.task.IGroup;
import com.lenny.framedemo.common.task.ITaskBackGround;
import com.lenny.framedemo.common.task.ITaskCallBack;
import com.lenny.framedemo.common.task.TaskScheduler;
import com.lenny.framedemo.common.utils.lang.Maps;
import com.lenny.framedemo.compent.http.Api;
import com.lenny.framedemo.compent.thread.ITask;

import java.util.Map;

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

    /**
     * 串行提交任务
     */
    public static <T> AsyncTaskInstance<T> submitTaskSeria(String taskName, String groupName, ITaskBackGround<T> task, ITaskCallBack<T> callBack) {
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(task, callBack)
                .taskName(taskName)
                .groupName(groupName)
                .seriaExecute(true);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }

    /*
     * 向默认分组提交任务
     */
    public static <T> AsyncTaskInstance<T> submitTaskSeria(String taskName, ITaskBackGround<T> task, ITaskCallBack<T> callBack) {
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(task, callBack)
                .taskName(taskName)
                .groupName(IGroup.DEFAULT_GROUP_NAME)
                .seriaExecute(true);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }


    /*
     * 向默认分组提交任务
     */
    public static AsyncTaskInstance apiCall(final Api api, final Map<String, Object> params, ITaskCallBack callBack) {
        String taskName = "execute" + api.getUrl();
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(new ITaskBackGround() {

            @Override
            public IResult onBackGround() throws Exception {
                return HttpHelper.execute(api, params);
            }
        }, callBack)
                .taskName(taskName)
                .seriaExecute(false);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }

    public static AsyncTaskInstance apiCall(final Api api, ITaskCallBack callBack) {
        String taskName = "execute" + api.getUrl();
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(new ITaskBackGround() {

            @Override
            public IResult onBackGround() throws Exception {
                return HttpHelper.execute(api, Maps.mapNull);
            }
        }, callBack)
                .taskName(taskName)
                .seriaExecute(false);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }

    /*
    * 分组提交任务
    */
    public static AsyncTaskInstance apiCall(final IGroup iGroup, final Api api, final Map<String, Object> params, ITaskCallBack callBack) {
        String taskName = "execute" + api.getUrl();
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(new ITaskBackGround() {

            @Override
            public IResult onBackGround() throws Exception {
                return HttpHelper.execute(api, params);
            }
        }, callBack)
                .taskName(taskName)
                .groupName(iGroup.groupName())
                .seriaExecute(false);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }

    public static AsyncTaskInstance apiCall(final IGroup iGroup, final Api api, ITaskCallBack callBack) {
        String taskName = "execute" + api.getUrl();
        AsyncTaskInstance asyncTaskInstance = AsyncTaskInstance.build(new ITaskBackGround() {

            @Override
            public IResult onBackGround() throws Exception {
                return HttpHelper.execute(api, Maps.mapNull);
            }
        }, callBack)
                .taskName(taskName)
                .groupName(iGroup.groupName())
                .seriaExecute(false);
        taskScheduler.submit(asyncTaskInstance);
        return asyncTaskInstance;
    }
}
