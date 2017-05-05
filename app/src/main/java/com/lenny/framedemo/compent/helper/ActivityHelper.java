package com.lenny.framedemo.compent.helper;

import android.app.Activity;

import com.lenny.framedemo.project.home.activity.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class ActivityHelper {

    private static final List<Activity> ACTIVITYS = Collections.synchronizedList(new ArrayList<Activity>());

    public static void create(Activity activity) {
        ACTIVITYS.add(activity);
    }

    public static void destroy(Activity activity) {
        ACTIVITYS.remove(activity);
    }


    public static void finishAll() {
        for (Activity activity : ACTIVITYS) {
            activity.finish();
        }
    }

    public static Activity last() {
        int size = ACTIVITYS.size();
        if (size >= 1) {
            return ACTIVITYS.get(size - 1);
        }
        return null;
    }

    /**
     * 是否为最后的activity
     *
     * @param activity
     * @return
     */
    public static boolean isLast(Activity activity) {
        return activity == last();
    }

    public static int size() {
        return ACTIVITYS.size();
    }

    /**
     * 是否包含HomeActivity
     */
    public static boolean containsHomeActivity() {
        for (Activity activity : ACTIVITYS) {
            if (activity instanceof MainActivity) {
                if (activity.isFinishing()) {
                    return false;
                }
                return true;
            }
            break;
        }
        return false;
    }

}
