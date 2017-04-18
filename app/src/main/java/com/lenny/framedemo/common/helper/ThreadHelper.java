package com.lenny.framedemo.common.helper;


import android.os.Handler;
import android.os.Looper;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public final class ThreadHelper {
    public final static Handler MAIN = new Handler(Looper.myLooper());

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void postMain(Runnable runnable) {
        MAIN.post(runnable);
    }

    public static void postMainDelay(Runnable runnable, long delayTime) {
        MAIN.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {

                }
            }
        }, delayTime);
    }

    public static void removeCallBacks(Runnable runnable) {
        MAIN.removeCallbacks(runnable);
    }
}
