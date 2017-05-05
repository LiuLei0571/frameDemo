package com.lenny.framedemo.compent.base;

import com.lenny.framedemo.common.task.IGroup;
import com.lenny.framedemo.compent.helper.TaskHelper;
import com.lenny.framedemo.compent.thread.ITask;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class BaseSimplePresenter {
    IGroup mIGroup;

    public BaseSimplePresenter(IGroup IGroup) {
        mIGroup = IGroup;
    }

    public final <T> void submitTask(String taskName, ITask<T> task) {
        TaskHelper.submitTask(taskName,mIGroup.groupName(),task,task);
    }
}
