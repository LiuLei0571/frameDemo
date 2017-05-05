package com.lenny.framedemo.common.task;

/**
 * 用途：分组任务
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public interface IGroupedTaskInstance extends ITaskInstance, IGroup {
    String DEFAULT_TASK_NAME = "at";
    /**
     * 丢弃新任务
     */
    int DISCARD_NEW = 0;
    /**
     * 取消老任务
     */
    int CANCEL_OLD = 1;
    /**
     * 强制提交，任务可重复
     */
    int FORCE_SUBMIT = 2;

    /**
     * 任务名称
     *
     * @return
     */
    String taskName();

    /**
     * 是否线性执行
     *
     * @return
     */
    boolean serialExecute();

    /**
     * 任务去重策略
     *
     * @return
     */
    int dualPolicy();
}
