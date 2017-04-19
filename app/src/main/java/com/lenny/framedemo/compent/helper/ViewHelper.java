package com.lenny.framedemo.compent.helper;

import android.os.Parcelable;
import android.view.View;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class ViewHelper {
    public static void bind(View view) {
        ButterKnife.bind(view);
    }

    public static void bind(Object target, View view) {
        ButterKnife.bind(target, view);
    }

    public static void unbind(Object view) {
        ButterKnife.unbind(view);
    }

    public static <T extends View> Parcelable saveInstanceState(T view, Parcelable parcelable) {
        return Icepick.saveInstanceState(view, parcelable);
    }

    public static <T extends View> Parcelable restoreInstanceState(T view, Parcelable parcelable) {
        return Icepick.restoreInstanceState(view, parcelable);
    }
}