package com.lenny.framedemo.compent.base;

import android.support.annotation.Nullable;
import android.view.View;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.ui.HeadBar;

import butterknife.Bind;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public abstract class TempleActivity extends BaseActivity {
    @Nullable
    @Bind(R.id.header_)
    HeadBar mHeadBar;

    @Override
    public void bindView(View view) {
        super.bindView(view);
        if (mHeadBar != null) {
            mHeadBar.setTitle("...");
            mHeadBar.setOnBackClick(new HeadBar.OnBackClick() {
                @Override
                public void onBackClick() {
                    onToolbarBackPress();
                }
            });
        }
    }

    public void setTitle(String title) {
        if (mHeadBar != null) {
            mHeadBar.setTitle(title);

        }
    }

    public void setTitle(int titleId) {
        if (mHeadBar != null) {
            mHeadBar.setTitle(titleId);

        }
    }

    public void setBackVisible(boolean show) {
        if (mHeadBar != null) {
            mHeadBar.setBackVisable(show);
        }

    }

    public void addText(String title, View.OnClickListener mListener) {
        if (mHeadBar != null) {
            mHeadBar.addText(title, mListener);
        }
    }

    public void addImage(int drawableId, View.OnClickListener mListenter) {
        if (mHeadBar != null) {
            mHeadBar.addImage(drawableId, mListenter);
        }
    }

    protected void onToolbarBackPress() {
        onBackPressed();
    }
}
