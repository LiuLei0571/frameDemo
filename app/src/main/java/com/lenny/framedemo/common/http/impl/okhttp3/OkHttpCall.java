package com.lenny.framedemo.common.http.impl.okhttp3;

import com.lenny.framedemo.common.http.ApiCall;
import com.lenny.framedemo.common.http.IRequest;
import com.lenny.framedemo.common.http.IResponse;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class OkHttpCall extends ApiCall {
    public Call call;
    Response response;
    public OkHttpCall(IRequest httpRequest, Call call) {
        super(httpRequest);
        this.call = call;
        setReady();
    }


    @Override
    protected void doCancel() {
        call.cancel();
    }

    @Override
    protected IResponse doExecute() throws Exception {
        response = call.execute();
        return new OkHttpResponse(response);
    }
}
