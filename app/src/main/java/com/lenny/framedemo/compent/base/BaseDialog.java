package com.lenny.framedemo.compent.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseDialog  extends Dialog implements IView,ILoading,IDialog{
    private BaseActivity mBaseActivity;
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        if (context instanceof BaseActivity) {
            mBaseActivity= (BaseActivity) context;
        }
    }

    @Override
    public void show() {
        super.show();
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
    public String groupName() {
        return mBaseActivity.getLocalClassName()+this.toString();
    }

}
