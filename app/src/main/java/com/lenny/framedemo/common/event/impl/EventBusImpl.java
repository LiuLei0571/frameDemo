package com.lenny.framedemo.common.event.impl;

import com.lenny.framedemo.common.event.IEvent;

import de.greenrobot.event.EventBus;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public class EventBusImpl implements IEvent {
    private EventBus mEventBus;

    public EventBusImpl(EventBus eventBus) {
        mEventBus = eventBus;
    }

    @Override
    public void register(Object obj) {
        mEventBus.register(obj);
    }

    @Override
    public void unregister(Object obj) {
        mEventBus.unregister(obj);
    }

    @Override
    public void post(Object obj) {
        mEventBus.post(obj);
    }
}
