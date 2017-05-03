package com.lenny.framedemo.common.thread;

/**
 * 用途：
 * Created by milk on 17/4/25.
 * 邮箱：649444395@qq.com
 */

public class ThreadLocalHelper {
    private static ThreadLocal<TaskInfo> sInfoThreadLocal = new ThreadLocal<>();

    public static TaskInfo getInfoThreadLocal() {
        return sInfoThreadLocal.get();
    }

    public static void setInfoThreadLocal(String groupName, String taskName) {
        sInfoThreadLocal.set(new TaskInfo(groupName, taskName));
    }

    public static class TaskInfo {
        public String taskName;
        public String groupName;

        public TaskInfo(String groupName, String taskName) {
            this.taskName = taskName;
            this.groupName = groupName;
        }
    }
}
