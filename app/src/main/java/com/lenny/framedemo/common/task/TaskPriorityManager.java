package com.lenny.framedemo.common.task;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 任务优先级管理器
 */
public final class TaskPriorityManager {
    final Map<String, List<AbstractTaskInstance<?>>> taskGroups;
    final BlockingQueue<Runnable> poolWorkQueue;
    private TaskScheduler taskScheduler;

    TaskPriorityManager(TaskScheduler taskScheduler, BlockingQueue<Runnable> poolWorkQueue) {
        this.taskScheduler = taskScheduler;
        this.taskGroups = new ConcurrentHashMap<>();
        this.poolWorkQueue = poolWorkQueue;
    }

    public void addTask(AbstractTaskInstance<?> task) {
        getByGroup(task.groupName()).add(task);
    }

    public List<AbstractTaskInstance<?>> getByGroup(String groupName) {
        List<AbstractTaskInstance<?>> group = taskGroups.get(groupName);
        if (group == null) {
            group = new CopyOnWriteArrayList<>();
            taskGroups.put(groupName, group);
        }
        return group;
    }

    public AbstractTaskInstance getOldTask(AbstractTaskInstance<?> task) {
        List<AbstractTaskInstance<?>> tasks = taskGroups.get(task.groupName());
        if (tasks == null || tasks.isEmpty()) return null;
        for (AbstractTaskInstance<?> t : tasks) {
            if (t.equals(task)) return t;
        }
        return null;
    }

    public void removeTask(AbstractTaskInstance<?> task) {
        List<AbstractTaskInstance<?>> group = taskGroups.get(task.groupName());
        if (group == null || group.isEmpty()) return;
        group.remove(task);
        if (group.isEmpty()) {
            taskGroups.remove(task.groupName());
        }
    }

    /**
     * 选取策略：首先从所有分组中随机选取一个分组
     * 如果该组中的任务为空，那么找出第一个不为空的组返回
     *
     * @return 下一个可用的任务分组
     */
    public List<AbstractTaskInstance<?>> getNextTaskGroup() {
        Collection<List<AbstractTaskInstance<?>>> allGroups = taskGroups.values();//所有的任务分组
        if (allGroups.isEmpty()) return null;
        List<AbstractTaskInstance<?>> next = null, firsNotEmptyList = null;
        int randomIndex = (int) (Math.random() * allGroups.size()), i = 0;
        for (List<AbstractTaskInstance<?>> group : allGroups) {
            if (firsNotEmptyList == null && group.size() > 0) {
                firsNotEmptyList = group;
                //第一个不为空的组和next都被找到则退出循环
                if (next != null) break;
            }
            //循环到对应的位置，设置当前组为next
            if (randomIndex == i++) {
                next = group;
                //第一个不为空的组和next都被找到则退出循环
                if (firsNotEmptyList != null) break;
            }
        }
        //如果next为空，则设置firsNotEmptyList为next
        if (next == null || next.isEmpty()) {
            next = firsNotEmptyList;
        }
        return next;
    }

    public void changePriority(AbstractTaskInstance<?> task, int priority) {
        if (poolWorkQueue.isEmpty()) return;
        if (task.status != ITaskInstance.STATUS_READY) return;
        if (poolWorkQueue.remove(task)) {
            task.priority = priority;
            taskScheduler.submitReadyTask(task);
        }
    }


//    /**
//     * 更改某一分组的任务优先级
//     *
//     * @param status
//     * @param groupName
//     */
//    public void changeGroupPriority(int status, String groupName) {
//        List<AbstractTaskInstance<?>> tasks = taskGroups.get(groupName);
//        if (tasks == null || tasks.isEmpty()) return;
//        switch (status) {
//            case TaskStatus.STATUS_START://UI启动恢复被降级的任务
//                for (AbstractTaskInstance<?> task : tasks) {
//                    if (task.priority != IPriorityTask.PRIOR_NORMAL) {//只恢复被onStop事件降级的任务
//                        changePriority(task, task.priority + 1);
//                    }
//                }
//                break;
//            case TaskStatus.STATUS_STOP://UI停止时降级其下的任务
//                for (AbstractTaskInstance<?> task : tasks) {
//                    changePriority(task, task.priority - 1);
//                }
//                break;
//            case TaskStatus.STATUS_DESTROY://UI销毁时取消由其发起的所有任务
//                for (AbstractTaskInstance<?> task : tasks) {
//                    taskScheduler.stopTask(task);
//                }
//                break;
//        }
//    }

    /**
     * 更改某一个任务的优先级
     *
     * @param status
     * @param groupName
     * @param taskName
     */
    public void changeTaskPriority(int status, String groupName, String taskName) {
        List<AbstractTaskInstance<?>> tasks = taskGroups.get(groupName);
        if (tasks == null || tasks.isEmpty()) {
            return;
        }
        switch (status) {
            case TaskStatus.STATUS_START://UI启动恢复被降级的任务
                for (AbstractTaskInstance<?> task : tasks) {
                    if (taskName != null && !taskName.equals(task.taskName())) {
                        continue;
                    }
                    if (task.priority != IPriorityTask.PRIOR_NOMAL) {//只恢复被onStop事件降级的任务
                        changePriority(task, task.priority + 1);
                    }
                }
                break;
            case TaskStatus.STATUS_STOP://UI停止时降级其下的任务
                for (AbstractTaskInstance<?> task : tasks) {
                    if (taskName != null && !taskName.equals(task.taskName())) {
                        continue;
                    }
                    changePriority(task, task.priority - 1);
                }
                break;
            case TaskStatus.STATUS_DESTROY://UI销毁时取消由其发起的所有任务
                for (AbstractTaskInstance<?> task : tasks) {
                    if (taskName != null && !taskName.equals(task.taskName())) {
                        continue;
                    }
                    taskScheduler.stopTask(task);
                }
                break;
        }
    }
}
