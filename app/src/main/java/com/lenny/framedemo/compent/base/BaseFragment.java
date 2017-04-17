package com.lenny.framedemo.compent.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseFragment extends Fragment implements IFragment, ILoading {
    private BaseActivity mActivity;
    @Override
    public String groupName() {
        return getClass().getSimpleName() + this.toString();
    }

    @Override
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity= (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
