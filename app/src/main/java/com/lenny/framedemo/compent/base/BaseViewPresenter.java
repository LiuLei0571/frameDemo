package com.lenny.framedemo.compent.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseViewPresenter<T extends IView> extends BaseSimplePresenter {
    public BaseViewPresenter(IView iView) {
        super(iView);
    }

    @Inject
    BaseActivity mBaseActivity;
    protected T mVIew;

    public T getVIew() {
        return mVIew;
    }

    public void setVIew(T VIew) {
        mVIew = VIew;
    }

    protected Context getContext() {
        return mBaseActivity;
    }

    protected BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    protected ILoading getILoading() {
        return mBaseActivity;
    }

    protected void showLoading() {
        mBaseActivity.showLoading();
    }

    protected void dismissLoading() {
        mBaseActivity.dismissLoading();
    }

    protected Intent getIntent() {
        return mBaseActivity.getIntent();
    }

    protected void finish() {
        mBaseActivity.finish();
    }

    protected FragmentManager getSupporFragmentManager() {
        return getBaseActivity().getSupportFragmentManager();
    }
}
