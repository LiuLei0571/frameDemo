package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.ContentType;
import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.ParamType;
import com.lenny.framedemo.common.http.RequestMethod;
import com.lenny.framedemo.compent.constants.Hosts;
import com.lenny.framedemo.compent.constants.ParamBuilders;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class Api implements IApi {
    private String path;
    private String defaultParams;
    private RequestMethod mRequestMethod;
    private Type mType;
    private ParamType mParamType;
    private ContentType mContentType;
    private IParamBuilder mIParamBuilder;
    private IHost mIHost= Hosts.defaults;
    private boolean enableCache = true;
    private String url;
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public Api setLogin(boolean login) {
        isLogin = login;
        return this;
    }

    @Override
    public String getUrl() {
        if (url == null) {
            //返回字符串对象的规范化表示形式
            return (mIHost.getHost() + path).intern();
        }
        return url;
    }

    public IParamBuilder getIParamBuilder() {
        return mIParamBuilder;
    }

    @Override
    public RequestMethod getMethod() {
        return mRequestMethod;
    }

    @Override
    public Type getResultType() {
        return mType;
    }

    @Override
    public ContentType getContentType() {
        return mContentType;
    }

    @Override
    public ParamType getParamType() {
        return mParamType;
    }

    @Override
    public String getDefaultParams() {
        return defaultParams;
    }

    @Override
    public IHost getHost() {
        return mIHost;
    }

    @Override
    public boolean enableCache() {
        return enableCache;
    }

    public Api setDefaultParams(String defaultParams) {
        this.defaultParams = defaultParams;
        return this;
    }

    public Api setType(Type type) {
        mType = type;
        return this;

    }

    public Api setIParamBuilder(IParamBuilder IParamBuilder) {
        mIParamBuilder = IParamBuilder;
        return this;

    }

    public Api setIHost(IHost IHost) {
        mIHost = IHost;
        return this;

    }

    public Api setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
        return this;

    }

    public static Api GET(String path) {
        Api api = new Api();
        api.path = path;
        api.mRequestMethod = RequestMethod.Get;
        api.mContentType = ContentType.APP_FORM_URLENCODED;
        api.mParamType = ParamType.normal;
        api.mIParamBuilder = ParamBuilders.normal;
        return api;
    }

    public static Api Post(String path, Type type) {
        Api api = new Api();
        api.path = path;
        api.mType = type;
        api.mRequestMethod = RequestMethod.Post;
        api.mContentType = ContentType.APP_JSON;
        api.mParamType = ParamType.json;
        api.mIParamBuilder = ParamBuilders.normal;
        return api;
    }

    public Api PostJson(String path, Type type) {
        Api api = new Api();
        api.path = path;
        api.mType = type;
        api.mRequestMethod = RequestMethod.Post;
        api.mContentType = ContentType.APP_JSON;
        api.mParamType = ParamType.json;
        api.mIParamBuilder = ParamBuilders.normal;
        return api;
    }

    public Api PostFile(String path, Type type) {
        Api api = new Api();
        api.path = path;
        api.mRequestMethod = RequestMethod.Post;
        api.mContentType = ContentType.APP_OCTET_STREAM;
        api.mParamType = ParamType.file;
        api.mIParamBuilder = ParamBuilders.normal;
        return api;
    }
}
