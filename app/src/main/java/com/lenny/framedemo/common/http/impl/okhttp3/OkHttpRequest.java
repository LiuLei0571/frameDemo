package com.lenny.framedemo.common.http.impl.okhttp3;

import com.bumptech.glide.request.Request;
import com.lenny.framedemo.common.http.IRequest;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class OkHttpRequest {
    private IRequest mIRequest;
    private Request mRequest;

    public OkHttpRequest(IRequest IRequest, Request request) {
        mIRequest = IRequest;
        mRequest = request;
    }

    public IRequest getIRequest() {
        return mIRequest;
    }

    public Request getRequest() {
        return mRequest;
    }
}
