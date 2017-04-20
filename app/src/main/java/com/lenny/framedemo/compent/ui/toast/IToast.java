package com.lenny.framedemo.compent.ui.toast;

import android.view.View;

/**
 * 用途：
 * Created by milk on 16/8/24.
 * 邮箱：649444395@qq.com
 */
public interface IToast {
    IToast setGravity(int gravity, int xOffset, int yOffset);

    IToast setDuration(long time);

    IToast setView(View view);

    IToast setMargin(float horizontalMargin, float verticalMargin);

    IToast setText(String text);

    void show();

    void cancel();
}
