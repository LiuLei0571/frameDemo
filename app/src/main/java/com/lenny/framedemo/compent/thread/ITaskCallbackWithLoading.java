package com.lenny.framedemo.compent.thread;

import com.lenny.framedemo.common.task.ITaskCallBack;
import com.lenny.framedemo.compent.base.ILoading;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/4.
 * 邮箱：liulei2@aixuedai.com
 */


public interface ITaskCallbackWithLoading<T> extends ITaskCallBack<T> {
    void setLoading(ILoading loading);
}
