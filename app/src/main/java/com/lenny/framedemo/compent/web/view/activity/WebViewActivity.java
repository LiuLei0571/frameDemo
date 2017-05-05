package com.lenny.framedemo.compent.web.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.base.BaseActivity;
import com.lenny.framedemo.compent.cdi.cmp.ActivityComponent;
import com.lenny.framedemo.compent.web.view.fragment.WebViewFragment;

public class WebViewActivity extends BaseActivity implements WebViewFragment.OnReceivedTitleListener {
    public String url;
    private FrameLayout mFragmentLayout;
    private ImageView mImageView;
    private WebViewFragment mWebViewFragment;
    private static final String WEB_TYPE = "web";


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
        initView(saveInstanceState);
        initData(saveInstanceState);
    }

    protected void initView(Bundle savedInstanceState) {
        mFragmentLayout = (FrameLayout) findViewById(R.id.container);
        mImageView = (ImageView) findViewById(R.id.ic_close_web);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

    }

    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mWebViewFragment = new WebViewFragment();

            mWebViewFragment.setArguments(getIntent().getExtras());
            getSupportsFragmentManager().beginTransaction().replace(R.id.container, mWebViewFragment, WEB_TYPE).commit();
        } else {
            mWebViewFragment = (WebViewFragment) getSupportFragmentManager().findFragmentByTag(WEB_TYPE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        close();
    }

    private void close() {
        finish();
    }

    @Override
    public void setTitle(String title) {

    }
}
