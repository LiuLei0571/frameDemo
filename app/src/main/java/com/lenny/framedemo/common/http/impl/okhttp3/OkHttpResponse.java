package com.lenny.framedemo.common.http.impl.okhttp3;

import com.lenny.framedemo.common.http.IResponse;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class OkHttpResponse implements IResponse {
    protected Response response;
    private String body;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String getBody() {
        if (body == null) {
            try {
                body = response.body().string();
            } catch (Exception e) {

            }
        }
        return body;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return response.body().byteStream();
    }
}
