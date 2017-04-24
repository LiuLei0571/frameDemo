package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.IRequest;

import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class DemoHttpRequest implements IRequest {
    @Override
    public Map<String, Object> getParams() {
        return null;
    }

    @Override
    public IApi getAPi() {
        return null;
    }

    @Override
    public String getdefaultParams() {
        return null;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public boolean enableCache() {
        return false;
    }
}
