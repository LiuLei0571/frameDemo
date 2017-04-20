package com.lenny.framedemo.compent.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.cdi.CDI;
import com.lenny.framedemo.compent.cdi.cmp.DialogComponent;
import com.lenny.framedemo.compent.helper.LoadingHelper;

import butterknife.ButterKnife;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseDialog extends Dialog implements IView, ILoading, IDialog {
    protected PresenterConnector mPresenterContorl;
    protected DialogComponent dialogComponent;
    private BaseActivity mBaseActivity;
    protected Bundle saveInstance;

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.LoginDialogStyle);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void show() {
        inits();
        super.show();
    }

    private void inits() {
        if (mPresenterContorl == null) {
            mPresenterContorl = new PresenterConnector();
        }
        if (dialogComponent == null) {
            dialogComponent = CDI.createDialogComponent(this);
        }
        if (saveInstance == null) {
            saveInstance = new Bundle();
        }
        doInject(this);
        View view = getLayoutInflater().inflate(getRootView(), null);
        beforeViewBind(view);
        bindView(view);
        setContentView(view);
        afterViewBind(saveInstance);
        mPresenterContorl.bindPresenter(saveInstance, null);
    }

    @Override
    public void savePresenter(BasePresenter presenter) {
        mPresenterContorl.savePresenter(presenter);
    }

    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        Bundle bundle = super.onSaveInstanceState();
        mPresenterContorl.onSaveInstanceState_(bundle);
        return bundle;
    }


    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        this.saveInstance = savedInstanceState;
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public Activity getActivity() {
        return mBaseActivity;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    @Override
    public void beforeViewBind(View rootView) {

    }

    @Override
    public void afterViewBind(Bundle saveInstanceState) {

    }

    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void unBindView() {
        ButterKnife.unbind(this);
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

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        FragmentActivity activity = (FragmentActivity) getActivity();
        if (activity != null) {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public String groupName() {
        return mBaseActivity.getLocalClassName() + this.toString();
    }

    protected abstract DialogComponent doInject(BaseDialog baseDialog);

    protected abstract int getRootView();
}
