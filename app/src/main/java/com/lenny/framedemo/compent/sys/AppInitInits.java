package com.lenny.framedemo.compent.sys;

import android.content.Context;

import com.lenny.framedemo.compent.sys.impl.CrashHandlerInit;
import com.lenny.framedemo.compent.sys.impl.FrameWorkInit;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public enum AppInitInits {
    framework("框架", FrameWorkInit.class),
    crash("异常", CrashHandlerInit.class);
    private String name;
    private Class<? extends IAppinits> initCls;
    private boolean isInit = false;

    AppInitInits(Class<? extends IAppinits> initCls) {
        this.initCls = initCls;
    }

    AppInitInits(String name, Class<? extends IAppinits> initCls) {
        this.name = name;
        this.initCls = initCls;
    }

    public static AppInitInits[] init_app = new AppInitInits[]{
            framework, crash
    };

    public String getName() {
        return name;
    }
    public static void doInit(Context context,AppInitInits... appInits){
        if (appInits != null) {
            long time= System.currentTimeMillis();
            for (AppInitInits app:appInits) {
                if (app.doInit(context)) {
                    long end=System.currentTimeMillis();

                }
            }
        }
    }
    private boolean doInit(Context context) {
        if (!isInit) {
            isInit = true;
            try {
                IAppinits iAppinits = initCls.newInstance();
                iAppinits.init(context);
            } catch (Exception e) {

            }
            return true;
        }
        return false;
    }
}

