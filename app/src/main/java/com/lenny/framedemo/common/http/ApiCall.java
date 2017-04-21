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

    @Override
    public IRequest getRequest() {
        return httpRequest;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void cancel() {
        if (status == STATUS_RUNNING) {
            try {
                doCancel();
            } catch (Exception e) {

            }
            status = STATUS_OVER;
        }
    }

    protected final void setReady() {
        if (status == SATUS_NEW) {
            status = STATUE_READY;
        }
    }

    @Override
    public IResponse execute() throws Exception {
        IResponse response = null;
        if (status == STATUE_READY) {
            status = STATUS_RUNNING;
            response = doExecute();
            status = STATUS_OVER;
        }
        return response;
    }


    protected abstract void doCancel();

    protected abstract IResponse doExecute() throws Exception;
}
