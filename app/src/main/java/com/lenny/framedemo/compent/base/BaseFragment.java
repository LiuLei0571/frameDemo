package com.lenny.framedemo.compent.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenny.framedemo.compent.cdi.CDI;
import com.lenny.framedemo.compent.cdi.cmp.FragmentComponent;
import com.lenny.framedemo.compent.helper.EventHelper;

import butterknife.ButterKnife;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseFragment extends Fragment implements IFragment, ILoading {
    private BaseActivity mActivity;
    private PresenterConnector mPresenterConnector;
    private FragmentComponent mFragmentComponent;
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mFragmentComponent == null) {
            mFragmentComponent = CDI.createFragmentComponent(this);
        }
        doInject(mFragmentComponent);
        if (mPresenterConnector == null) {
            mPresenterConnector = new PresenterConnector();
        }
        EventHelper.resigster(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getRootViewId(), container, false);
        }
        beforeViewBind(mView);
        bindView(mView);
        afterViewBind(savedInstanceState);
        mPresenterConnector.bindPresenter(savedInstanceState, getArguments());
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mPresenterConnector != null) {
            mPresenterConnector.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenterConnector != null) {
            mPresenterConnector.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventHelper.unRegister(this);
        if (mPresenterConnector != null) {
            mPresenterConnector.destory();
            mPresenterConnector = null;
        }
        unBindView();
        if (mView != null) {
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mView);
            }
            mView = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenterConnector != null) {
            mPresenterConnector.onSaveInstanceState_(outState);
        }
    }

    @Override
    public void beforeViewBind(View rootView) {

    }

    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public View findViewId(int id) {
        if (mView != null) {
            return mView.findViewById(id);
        }
        return null;
    }

    @Override
    public void savePresenter(BasePresenter presenter) {
        if (mPresenterConnector != null) {
            mPresenterConnector.savePresenter(presenter);
        }
    }


    @Override
    public void afterViewBind(Bundle saveInstanceState) {

    }

    @Override
    public void unBindView() {
        ButterKnife.unbind(this);
    }

    @Override
    public String groupName() {
        return getClass().getSimpleName() + this.toString();
    }

    @Override
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVistiAble();
        } else {
            onGone();
        }
    }

    private void onVistiAble() {
    }

    protected void onGone() {

    }

    protected abstract int getRootViewId();

    protected abstract void doInject(IFragment fg);
}

