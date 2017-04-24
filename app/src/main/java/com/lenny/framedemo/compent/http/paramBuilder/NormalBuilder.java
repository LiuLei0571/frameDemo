package com.lenny.framedemo.compent.http.paramBuilder;

import com.lenny.framedemo.compent.http.IParamBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class NormalBuilder implements IParamBuilder {
    @Override
    public Map<String, Object> buildParams(Map<String, Object> authHeader, Object params) {
        Map<String, Object> allParams = new HashMap<>();
        if (allParams != null) {
            allParams.putAll(authHeader);
        }
        if (params != null) {
            allParams.putAll(toMap(params));
        }

        return null;
    }

    private Map<String, Object> toMap(Object object) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (object instanceof Map) {
                result = (Map<String, Object>) object;
            }
        } catch (Exception e) {

        }
        return result;
    }
}
