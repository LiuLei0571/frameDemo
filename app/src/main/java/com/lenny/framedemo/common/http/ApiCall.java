package com.lenny.framedemo.common.http;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public abstract class ApiCall implements ICall {
    private int status = SATUS_NEW;
    private IRequest httpRequest;

    public ApiCall(IRequest httpRequest) {

        this.httpRequest = httpRequest;
    }

    public IRequest getHttpRequest() {
        return httpRequest;
    }

    @Override
    public int getStatus() {
        return status;
    }


    protected abstract void doCancle();

    protected abstract IResponse doExecute() throws Exception;
}
