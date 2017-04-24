package com.lenny.framedemo.compent.http.paramBuilder;

import com.lenny.framedemo.compent.http.IParamBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class NewBuilder implements IParamBuilder {
    @Override
    public Map<String, Object> buildParams(Map<String, Object> authHeader, Object params) {
        Map<String, Object> result = new HashMap<>();
        result.put("auth", authHeader);
        result.put("request", params);
        return result;
    }
}
