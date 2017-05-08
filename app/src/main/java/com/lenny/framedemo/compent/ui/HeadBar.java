package com.lenny.framedemo.compent.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.base.BaseLinearLayout;
import com.lenny.framedemo.compent.helper.UIHelper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/8.
 * 邮箱：liulei2@aixuedai.com
 */


public class HeadBar extends BaseLinearLayout {
    @Bind(R.id.toolbar_back)
    ImageButton mToolbarBack;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar_right_text)
    TextView mToolbarRightText;
    @Bind(R.id.toolbar_right)
    LinearLayout mRight;
    @Bind(R.id.rlyt_toolbar)
    RelativeLayout mRlytToolbar;
    @Bind(R.id.line)
    View mLine;
    private OnBackClick mOnBackClick;

    public void setOnBackClick(OnBackClick onBackClick) {
        mOnBackClick = onBackClick;
    }

    public HeadBar(Context context) {
        this(context, null);
        init(context, null);
    }

    public HeadBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.item_widget_headbar;
    }

    public interface OnBackClick {
        void onBackClick();
    }

    @Override
    public void init(Context context, AttributeSet mAttributeSet) {
        super.init(context, mAttributeSet);
    }

    @OnClick(R.id.toolbar_back)
    public void onViewClicked() {
        if (mOnBackClick != null) {
            mOnBackClick.onBackClick();

        }
    }

    public void setTitle(String name) {
        mToolbarTitle.setText(name);
    }

    public void setTitle(int stringId) {
        mToolbarTitle.setText(stringId);
    }

    public void setBackVisable(boolean showBack) {
        if (showBack) {
            mToolbarBack.setVisibility(VISIBLE);
        } else {
            mToolbarBack.setVisibility(GONE);
        }
    }

    public ImageButton addImage(int drawable, OnClickListener listener) {
        ImageButton imageView = null;
        if (mRight != null) {
            imageView = new ImageButton(getContext());
            imageView.setImageResource(drawable);
            imageView.setOnClickListener(listener);
            imageView.setBackgroundResource(R.drawable.item_selector);
            imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setPadding(getPx(10), 0, getPx(10), 0);
            mRight.addView(imageView);
        }
        return imageView;
    }


    public TextView addText(int text, OnClickListener listener) {
        return addText(UIHelper.getString(text), listener);
    }


    private int getPx(int dp) {
        float mDensity = getResources().getDisplayMetrics().density;
        return (int) (mDensity * dp);
    }

    public TextView addText(String text, OnClickListener listener) {
        TextView textView = null;
        if (mRight != null) {
            textView = new TextView(getContext());
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            textView.setMinWidth(getPx(44));
            textView.setPadding(getPx(15), 0, getPx(15), 0);
            textView.setGravity(Gravity.CENTER);
            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_text_major));
            textView.setOnClickListener(listener);
            textView.setBackgroundResource(R.drawable.item_selector);
            mRight.addView(textView);
        }
        return textView;
    }
}
