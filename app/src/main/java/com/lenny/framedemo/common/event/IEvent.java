package com.lenny.framedemo.common.event;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public interface IEvent {
    void register(Object obj);

    void unregister(Object obj);

    void post(Object obj);
}
