package com.lenny.framedemo.common.helper;

import com.lenny.framedemo.common.parse.IParse;
import com.lenny.framedemo.common.utils.lang.Strings;
import com.lenny.framedemo.compent.helper.CDIHelper;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class JsonHelper {
    public static IParse iParse;

    static {
        iParse = CDIHelper.getInstance().mIParse;
    }

    public static String toJSONString(Object object) {
        return iParse.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        String typeString = clazz.getSimpleName();
        if (isString(typeString)) {
            try {
                return (T) json;
            } catch (Exception e) {
                return null;
            }
        }
        return iParse.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        String typeString = Strings.EMPTY;
        if (type instanceof Class) {
            typeString = ((Class) type).getSimpleName();
        }
        if (isString(typeString)) {
            try {
                return (T) json;
            } catch (Exception e) {
                return null;
            }
        }
        return iParse.fromJson(json, type);
    }

    public static boolean isString(String type) {
        return type.startsWith("String");
    }
}
