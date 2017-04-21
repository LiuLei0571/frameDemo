package com.lenny.framedemo.common.http;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public interface IApi {
    String getUrl();

    RequestMethod getMethod();

    Type getResultType();

    ContentType getContentType();

    ParamType getParmType();

    String getDefaultParams();

    /**
     * 是否需要缓存
     *
     * @return
     */
    boolean enableCache();
}
