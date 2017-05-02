package com.lenny.framedemo.common.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public class ThreadUtil {
    private static Boolean isChildProcess;

    public static Boolean getIsChildProcess(Context context) {
        if (isChildProcess == null) {
            String procressName = getProcessName(context);
            isChildProcess = procressName != null && procressName.contains(":");
        }
        return isChildProcess;
    }

    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : mActivityManager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                return process.processName;
            }
        }
        return null;
    }

}
