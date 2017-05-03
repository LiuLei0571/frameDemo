package com.lenny.framedemo.common.task;

import java.util.Comparator;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public interface IPriorityTask extends Comparator<IPriorityTask> {
    int PRIOR_HIGHT = 256;
    int PRIOR_UI = 32;
    /**
     * 一般优先级
     */
    int PRIOR_NOMAL = 1;

    int getPriority();
}
