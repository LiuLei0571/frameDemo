package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.utils.lang.Strings;
import com.lenny.framedemo.compent.constants.Sps;

import java.lang.reflect.Type;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public class SPHelper {
    public static Sps sp = Sps.defaults;

    public static String getString(String key) {
        return sp.getString(key, Strings.EMPTY);
    }
    public static String getString(String key,String defaultValue) {
        return sp.getString(key,defaultValue);
    }

    public static void putString(String key, String value) {
        sp.putString(key, value);
    }

    public static int getInt(String key) {
        return sp.getInt(key);
    }

    public static void putInt(String key, int value) {
        sp.putInt(key, value);
    }

    public static long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public static long getLong(String key, long defaultLong) {
        return sp.getLong(key, defaultLong);
    }

    public static void putLong(String key, long value) {
        sp.putLong(key, value);
    }


    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static void putBoolean(String key, boolean value) {
        sp.putBoolean(key, value);
    }

    public static <T> void putBean(String key, T bean) {
        sp.putBean(key, bean);
    }

    public static <T> T getBean(String key, Type type) {
        return sp.getBean(key, type);
    }

    public static void remove(String key) {
        sp.remove(key);
    }
}
