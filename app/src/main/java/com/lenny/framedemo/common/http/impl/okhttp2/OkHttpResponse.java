package com.lenny.framedemo.common.http.impl.okhttp2;

import com.lenny.framedemo.common.http.IResponse;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class OkHttpResponse implements IResponse {
    protected Response response;
    public OkHttpResponse(Response response) {
        this.response=response;
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
