package com.lenny.framedemo.compent.thread;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.compent.base.ILoading;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/4.
 * 邮箱：liulei2@aixuedai.com
 */


public class ApiTask<Result> extends ApiCallback<Result> implements ITask<IResult<Result>> {
    public ApiTask() {
    }

    public ApiTask(ILoading loading) {
        super.setLoading(loading);
    }

    public void doFailure(final IResult<String> result) {
        if (ThreadHelper.isMainThread()) {
            onFailure(result);
        } else {
            ThreadHelper.postMain(new Runnable() {
                @Override
                public void run() {
                    onFailure(result);
                }
            });
        }
    }


    @Override
    public <T> T onBackGround() throws Exception {
        return null;
    }
}
