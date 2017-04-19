package com.lenny.framedemo.compent.base;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class PresenterConnector {
    private Set<BasePresenter> mBasePresenters;

    public final void bindPresenter(Bundle saveInstance, Bundle extras) {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.doCreate_(saveInstance, extras);
            }
        }
    }

    public synchronized void savePresenter(BasePresenter saveInstance) {
        if (mBasePresenters == null) {
            mBasePresenters = new HashSet<>();
        }
        mBasePresenters.add(saveInstance);
    }

    protected void onNewIntent(Intent intent) {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.onNewIntent(intent);
            }
        }
    }

    protected void onResume() {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.onResume();
            }
        }
    }

    protected void onPause() {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.onPause();
            }
        }
    }


    public final void onSaveInstanceState_(Bundle outSate) {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.onSaveInstanceState(outSate);
                presenter.doSaveInstanceState_(outSate);
            }
        }
    }

    protected void onActivityForResult(int requestCode, int resultCode, Intent data) {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.onActivityForResult(requestCode, resultCode, data);
            }
        }
    }

    public final void destory() {
        if (mBasePresenters != null) {
            for (BasePresenter presenter : mBasePresenters) {
                presenter.doDestroy();
            }
            mBasePresenters.clear();
            mBasePresenters = null;
        }
    }
}
