package com.lenny.framedemo.compent.base;

import android.content.Intent;
import android.os.Bundle;

import com.lenny.framedemo.compent.event.EmptyEvent;
import com.lenny.framedemo.compent.helper.EventHelper;

import icepick.Icepick;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public class BasePresenter<T extends IView> extends BaseViewPresenter<T> {
    public BasePresenter(IView iView) {
        super(iView);
        iView.savePresenter(this);
    }

    protected final void doCreate_(Bundle saveInstanceState, Bundle params) {
        Icepick.restoreInstanceState(this, saveInstanceState);
        if (params == null) {
            params = new Bundle();
        }
        initParams(params);
        EventHelper.resigster(this);
        onCreate(saveInstanceState);
        if (mIGroup == null) {
            mIGroup = mBaseActivity;
        }
    }

    protected final void doDestroy() {
        EventHelper.unRegister(this);
        onDestroy();
    }

    protected final void doSaveInstanceState_(Bundle outSate) {
        Icepick.saveInstanceState(this, outSate);
    }

    protected void initParams(Bundle params) {
    }

    protected void onCreate(Bundle saveInstanceState) {
    }

    protected void onNewIntent(Intent intent) {

    }

    protected void onResume() {

    }

    protected void onPause() {

    }

    protected void onDestroy() {
    }

    public void onEvent(EmptyEvent event) {
    }

    protected void startActivityForResult(Intent intent, int requestCode) {
        if (mVIew != null) {
            if (mVIew instanceof IFragment) {
                IFragment fragment = (IFragment) mVIew;
                fragment.startActivityForResult(intent, requestCode);
            } else {
                mBaseActivity.startActivityForResult(intent, requestCode);
            }
        }
    }
}
