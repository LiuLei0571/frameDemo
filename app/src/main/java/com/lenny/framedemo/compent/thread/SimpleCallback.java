package com.lenny.framedemo.compent.thread;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.compent.base.ILoading;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/4.
 * 邮箱：liulei2@aixuedai.com
 */


public class SimpleCallback<T> implements ITaskCallbackWithLoading<T> {
    protected ILoading loading;

    @Override
    public void setLoading(ILoading loading) {
        this.loading = loading;
    }

    @Override
    public void onBeforeCall() {
        if (loading != null) {
            loading.showLoading();

        }
    }

    @Override
    public void onAfterCall() {
        if (loading != null) {
            loading.dismissLoading();
        }
    }

    public void postProgressInfo(final Object object) {
        ThreadHelper.postMain(new Runnable() {
            @Override
            public void run() {
                try {
                    onProgressInfo(object);

                } catch (Exception e) {

                }
            }
        });
    }

    public void onProgressInfo(Object object) {
    }

    @Override
    public void onComplete(T result) {

    }


    @Override
    public void onException(Throwable throwable) {

    }

    @Override
    public void onCancelled() {

    }


}
