package com.lenny.framedemo.common.http.impl.okhttp3;

import com.lenny.framedemo.common.http.HttpResultParse;
import com.lenny.framedemo.common.http.HttpScheduler;
import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.ICall;
import com.lenny.framedemo.common.http.IRequest;
import com.lenny.framedemo.common.http.IResponse;
import com.lenny.framedemo.common.http.ParamType;
import com.lenny.framedemo.common.http.RequestMethod;
import com.lenny.framedemo.common.util.lang.Chares;
import com.lenny.framedemo.common.util.lang.Strings;

import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class OkHttpSchedule extends HttpScheduler {
    public static final MediaType JSON = MediaType.parse("application/json: charset=utf-8");
    private OkHttpClient mClient;

    public OkHttpSchedule(OkHttpClient client, HttpResultParse httpResultParse) {
        super(httpResultParse);
        this.mClient = client;
    }

    public OkHttpClient getClient() {
        if (mClient != null) {
            mClient = new OkHttpClient();
        }
        return mClient;
    }

    @Override
    protected IResponse doExecute(ICall iCall) throws Exception {
        return iCall.execute();
    }

    @Override
    public ICall newCall(IRequest httpRequest) {
        Map<String, Object> params = httpRequest.getParams();
        IApi api = httpRequest.getAPi();
        RequestMethod method = api.getMethod();
        StringBuilder urlStringBuilder = new StringBuilder();
        Request.Builder builder = new Request.Builder();
        if (httpRequest.getdefaultParams() != null) {
            urlStringBuilder.append(httpRequest.getdefaultParams());
        }
//        Iterator<Map.Entry<String, String>> headers = httpRequest.getHeaders().entrySet().iterator();
//        while (headers.hasNext()) {
//            Map.Entry<String, String> header = headers.next();
//            builder.addHeader(header.getKey(), header.getValue());
//        }
        switch (method) {
            case Get:
                if (params != null) {
                    if (urlStringBuilder.lastIndexOf(Strings.QMARK) == -1) {
                        urlStringBuilder.append(Chares.QMARK);
                    } else {
                        urlStringBuilder.append(Chares.AND);
                    }
                    Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();

                    while (it.hasNext()) {
                        Map.Entry<String, Object> entry = it.next();
                        urlStringBuilder.append(entry.getKey())
                                .append(Chares.EQUAL)
                                .append(entry.getValue());
                        if (it.hasNext()) {
                            urlStringBuilder.append(Chares.AND);
                        }

                    }
                    builder.get();
                }
                break;
            case Post:
                ParamType paramType = api.getParmType();
                switch (paramType) {
                    case normal:

                        break;
                    case json:
                        String json=Strings.EMPTY;
                        if(params!=null){
                        }
                        break;
                    case file:
                        break;
                    default:
                        break;
                }
                break;
            case Put:
                break;
            case Delete:
                break;
            default:
                break;
        }
        return null;
    }
}
