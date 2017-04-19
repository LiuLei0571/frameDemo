package com.lenny.framedemo.compent.base;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lenny.framedemo.compent.helper.ViewHelper;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseLinearLayout extends LinearLayout implements IWidgetView {
    public BaseLinearLayout(Context context) {
        this(context, null);
        init(context, null);
    }


    public BaseLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public BaseLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet mAttributeSet) {
    }

    @Override
    protected void onFinishInflate() {
        int rootViewId = getRootLayoutId();
        if (rootViewId != -1) {
            beforeViewBind(this);
            inflate(getContext(), rootViewId, this);
            ViewHelper.bind(this);
            afterViewBind(this);
        }
        super.onFinishInflate();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return ViewHelper.saveInstanceState(this, super.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(ViewHelper.restoreInstanceState(this, state));
    }

    @Override
    public void beforeViewBind(View view) {

    }

    @Override
    public void afterViewBind(View view) {

    }
}
