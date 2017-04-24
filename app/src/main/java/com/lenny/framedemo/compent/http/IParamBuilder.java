package com.lenny.framedemo.compent.http;

import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public interface IParamBuilder {
    Map<String,Object> buildParams(Map<String,Object> authHeader, Object params);
}
