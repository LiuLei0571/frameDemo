package com.lenny.framedemo.compent.web.view.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.base.BaseActivity;
import com.lenny.framedemo.compent.cdi.cmp.ActivityComponent;
import com.lenny.framedemo.compent.web.presenter.WebViewActivityPresenter;
import com.lenny.framedemo.compent.web.view.fragment.WebViewFragment;

import javax.inject.Inject;

public class WebViewActivity extends BaseActivity implements WebViewFragment.OnReceivedTitleListener {
    public String url;
    private ImageView mImageView;

    @Inject
    WebViewActivityPresenter presenter;
    @Override
    protected int getRootViewId() {
        return R.layout.activity_web;
    }

    @Override
    protected void doInject(ActivityComponent activityComponent) {
        activityComponent.plus(this);
    }

    @Override
    public void afterViewBind(Bundle saveInstanceState) {
        super.afterViewBind(saveInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        presenter.backPress();
    }

    private void close() {
        finish();
    }

    @Override
    public void setTitle(String title) {

    }
}
