package com.lenny.framedemo.common.parse;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public interface IParse {
    String toJson(Object bean);
    <T> T fromJson(String json,Class<T> clazz);
    <T> T fromJson(String json, Type type);
}
