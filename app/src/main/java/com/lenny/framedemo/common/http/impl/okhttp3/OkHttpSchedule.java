package com.lenny.framedemo.common.http.impl.okhttp3;

import com.lenny.framedemo.common.helper.JsonHelper;
import com.lenny.framedemo.common.http.HttpResultParse;
import com.lenny.framedemo.common.http.HttpScheduler;
import com.lenny.framedemo.common.http.IApi;
import com.lenny.framedemo.common.http.ICall;
import com.lenny.framedemo.common.http.IRequest;
import com.lenny.framedemo.common.http.IResponse;
import com.lenny.framedemo.common.http.ParamType;
import com.lenny.framedemo.common.http.RequestMethod;
import com.lenny.framedemo.common.utils.lang.Chars;
import com.lenny.framedemo.common.utils.lang.Strings;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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
        if (mClient == null) {
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
        StringBuilder urlStringBuilder = new StringBuilder(api.getUrl());
        Map<String, String> headers = httpRequest.getHeaders();
        Request.Builder builder = new Request.Builder();
        switch (method) {
            case Get:
                if (params != null) {
                    if (urlStringBuilder.lastIndexOf(Strings.QMARK) == -1) {
                        urlStringBuilder.append(Chars.QMARK);
                    } else {
                        urlStringBuilder.append(Chars.AND);
                    }
                    Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();

                    while (it.hasNext()) {
                        Map.Entry<String, Object> entry = it.next();
                        urlStringBuilder.append(entry.getKey())
                                .append(Chars.EQUAL)
                                .append(entry.getValue());
                        if (it.hasNext()) {
                            urlStringBuilder.append(Chars.AND);
                        }

                    }
                    builder.get();
                }
                break;
            case Post:
                ParamType paramType = api.getParamType();
                headers.put("Accept", "application/json");
                switch (paramType) {
                    case normal:
                        FormBody.Builder formBodyBuilder = new FormBody.Builder();
                        if (params != null) {
                            for (Map.Entry<String, Object> pair : params.entrySet()) {
                                Object object = pair.getValue();
                                formBodyBuilder.add(pair.getKey(), object == null ? Strings.EMPTY : object.toString());
                            }
                        }
                        RequestBody formBody = formBodyBuilder.build();
                        builder.post(formBody);
                        break;
                    case json:
                        String json = Strings.EMPTY;
                        if (params != null) {
                            json = JsonHelper.toJSONString(params);
                        }
                        RequestBody body = RequestBody.create(JSON, json);
                        builder.post(body);
                        break;
                    case file:
                        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();
                        if (params != null) {
                            for (Map.Entry<String, Object> pair : params.entrySet()) {
                                Object object = pair.getValue();
                                if (object instanceof File) {
                                    File file = (File) object;
                                    MediaType PNG = MediaType.parse("image/png");
                                    requestBodyBuilder.addFormDataPart(pair.getKey(), file.getName(), RequestBody.create(PNG, (File) object));
                                } else if (object instanceof byte[]) {
                                    MediaType stream = MediaType.parse("application/octet-stream; charset=utf-8");
                                    requestBodyBuilder.addFormDataPart(pair.getKey(), pair.getKey(), RequestBody.create(stream, (byte[]) object));
                                } else {
                                    requestBodyBuilder.addFormDataPart(pair.getKey(), object == null ? Strings.EMPTY : object.toString());
                                }
                            }
                        }
                        builder.post(requestBodyBuilder.build());
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
        if (headers != null) {
            builder.headers(Headers.of(headers));
        }
        Request request = builder.url(urlStringBuilder.toString()).build();
        Call call = getClient().newCall(request);
        OkHttpCall okHttpCall = new OkHttpCall(httpRequest, call);
        return okHttpCall;
    }
}
