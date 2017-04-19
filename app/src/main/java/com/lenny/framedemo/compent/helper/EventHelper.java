package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.event.IEvent;


/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class EventHelper {
    private static IEvent iEvent;

    static {
        iEvent = CDIHelper.getInstance().mIEvent;
    }

    public static void resigster(Object object) {
        iEvent.register(object);
    }

    public static void unRegister(Object object) {
        iEvent.unregister(object);
    }

    public static void post(Object object) {
        iEvent.post(object);
    }
}
