package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.IRequest;
import com.lenny.framedemo.common.http.ParamType;
import com.lenny.framedemo.common.utils.lang.Strings;
import com.lenny.framedemo.compent.constants.ParamBuilders;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class DemoHttpRequest implements IRequest {
    public static final String API_VERSION_KEY = "apiVersion";
    private Api mApi;
    private Object params;
    private IParamBuilder mIParamBuilder;
    private Boolean enableCache;

    public DemoHttpRequest(Api api) {
        mApi = api;
        this.mIParamBuilder = mApi.getIParamBuilder();
    }

    public DemoHttpRequest setIParamBuilder(IParamBuilder IParamBuilder) {
        mIParamBuilder = IParamBuilder;
        return this;
    }

    public DemoHttpRequest setEnbleCache(boolean enableCache) {
        this.enableCache = enableCache;
        return this;
    }

    public DemoHttpRequest setParams(Object params) {
        this.params = params;
        return this;
    }

    public DemoHttpRequest addParams(String key, String value) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (params instanceof Map) {
            try {
                Map<String, Object> map = (Map<String, Object>) params;
                map.put(key, value);
            } catch (Exception e) {
            }

        }
        return this;
    }

    @Override
    public Map<String, Object> getParams() {
        if (mIParamBuilder == null) {
            mIParamBuilder = ParamBuilders.nomal;
        }
        if (ParamBuilders.nomal == mIParamBuilder) {
            return mIParamBuilder.buildParams(getBaseParams(true), params);
        } else {
            return mIParamBuilder.buildParams(getBaseParams(false), params);

        }
    }

    private Map<String, Object> getBaseParams(boolean b) {
        Map<String, Object> params = new HashMap<>();
        return params;
    }

    @Override
    public IApi getAPi() {
        return mApi;
    }

    @Override
    public String getdefaultParams() {
        return Strings.EMPTY;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = getBaseHeader();
        return headers;
    }

    private Map<String, String> getBaseHeader() {
        Map<String, String> header = new HashMap<>();
        //添加头部信息
        return header;
    }

    @Override
    public boolean enableCache() {
        ParamType paramType=mApi.getParamType();
        if (ParamType.file==paramType) {
            return  false;
        }else if(enableCache!=null){
            return  enableCache;
        }else {
            return mApi.enableCache();
        }
     }

    public static DemoHttpRequest build(Api api) {
        return new DemoHttpRequest(api);
    }
}
