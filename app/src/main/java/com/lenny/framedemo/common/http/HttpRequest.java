package com.lenny.framedemo.common.http;

import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class HttpRequest {
    protected IApi api;
    protected Map<String, Object> params;
    protected String defaultParams;

    public IApi getApi() {
        return api;
    }

    public void setApi(IApi api) {
        this.api = api;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getDefaultParams() {
        return defaultParams;
    }

    public void setDefaultParams(String defaultParams) {
        this.defaultParams = defaultParams;
    }
}
