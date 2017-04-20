package com.lenny.framedemo.compent.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lenny.framedemo.R;

import java.util.Date;

/**
 * 用途：
 * Created by milk on 17/4/20.
 * 邮箱：649444395@qq.com
 */

public class Progress {
    private static Dialog mProgress = null;

    public static Dialog show(Context context, String msg) {
        try {
            dismiss();
            mProgress = new Dialog(context, R.style.LoadingDialog);
            mProgress.setCancelable(false);
            mProgress.setTitle(null);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_progress, null);
            view.setTag(new Date().getTime());
            if (!TextUtils.isEmpty(msg)) {
                ((TextView) view.findViewById(R.id.message)).setText(msg);
            }
            mProgress.setContentView(view);
            mProgress.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProgress;
    }

    public static void dismiss() {
        if (mProgress != null) {
            try {
                mProgress.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isShowing() {
        if (mProgress != null) return mProgress.isShowing();
        else return false;
    }


}
