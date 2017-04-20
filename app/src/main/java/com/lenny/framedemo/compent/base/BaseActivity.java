package com.lenny.framedemo.compent.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.cdi.CDI;
import com.lenny.framedemo.compent.cdi.cmp.ActivityComponent;
import com.lenny.framedemo.compent.event.EmptyEvent;
import com.lenny.framedemo.compent.helper.ActivityHelper;
import com.lenny.framedemo.compent.helper.EventHelper;
import com.lenny.framedemo.compent.helper.LoadingHelper;

import butterknife.ButterKnife;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity implements IView, ILoading {
    protected PresenterConnector mPresenterConnector;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHelper.create(this);
        EventHelper.resigster(this);
        if (mPresenterConnector == null) {
            mPresenterConnector = new PresenterConnector();
        }
        if (mActivityComponent == null) {
            mActivityComponent = CDI.createActivityComponent(this);
        }
        doInject(mActivityComponent);
        initParams(getIntent().getExtras());
        View view = getLayoutInflater().inflate(getRootViewId(), null, false);
        beforeViewBind(view);
        setContentView(view);
        bindView(view);
        afterViewBind(savedInstanceState);
        mPresenterConnector.bindPresenter(savedInstanceState, getIntent().getExtras());

    }

    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overrideOpenAnime();
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        overrideOpenAnime();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overrideOpenAnime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenterConnector != null) {
            mPresenterConnector.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenterConnector != null) {
            mPresenterConnector.onPause();
        }
    }

    @Override
    public void beforeViewBind(View rootView) {

    }

    @Override
    public void afterViewBind(Bundle saveInstanceState) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public View findViewId(int id) {
        return null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initParams(intent.getExtras());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventHelper.unRegister(this);
        ActivityHelper.destroy(this);
        if (mPresenterConnector != null) {
            mPresenterConnector.destory();
            mPresenterConnector = null;
        }
    }

    @Override
    public void unBindView() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenterConnector != null) {
            mPresenterConnector.onSaveInstanceState_(outState);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenterConnector != null) {
            mPresenterConnector.onActivityForResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void finish() {
        super.finish();
        ActivityHelper.destroy(this);
        overrideCloseAnime();
    }

    protected boolean animation = false;

    public void setTransitionAniAble(boolean isAni) {
        animation = isAni;
    }

    @Override
    public void savePresenter(BasePresenter presenter) {
        if (mPresenterConnector != null) {
            mPresenterConnector.savePresenter(presenter);
        }
    }

    protected void overrideOpenAnime() {
        if (animation) {
            overridePendingTransition(R.anim.in_from_right, R.anim.in_from_left);
        }
    }

    protected void overrideCloseAnime() {
        if (animation) {
            overridePendingTransition(R.anim.in_from_left, R.anim.in_from_right);
        }
    }

    @Override
    public void showLoading() {
        LoadingHelper.showLoading(getBaseActivity());
    }

    @Override
    public void showLoading(String content) {
        LoadingHelper.showLoading(getBaseActivity(),content);

    }

    @Override
    public void dismissLoading() {
        LoadingHelper.dismiss();
    }
    public void onEvent(EmptyEvent event) {
    }
    protected abstract int getRootViewId();

    /**
     * 初始化传参
     *
     * @param extras
     */
    protected void initParams(Bundle extras) {
    }

    protected abstract void doInject(ActivityComponent activityComponent);

    @Override
    public String groupName() {
        return getLocalClassName() + this.toString();
    }
}
