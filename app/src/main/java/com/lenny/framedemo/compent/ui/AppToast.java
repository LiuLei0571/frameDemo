package com.lenny.framedemo.compent.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.lenny.framedemo.R;
import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.compent.ui.toast.CustomToast;
import com.lenny.framedemo.compent.ui.toast.IToast;
import com.lenny.framedemo.compent.ui.toast.SystemToast;
import com.lenny.framedemo.compent.ui.toast.ToastCompat;


/**
 * 用途：
 * Created by milk on 16/8/24.
 * 邮箱：649444395@qq.com
 */
public class AppToast {
    private boolean isNotificationEnabled;

    public AppToast(Context context) {
        this.context = context.getApplicationContext();
        this.windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        isNotificationEnabled = ToastCompat.isNotificationEnabled(context);
    }

    /**
     * application context
     */
    private Context context;
    private IToast toast = null;
    private IToast imgToast = null;
    private View textLayout = null;
    private View textImgLayout = null;
    private WindowManager windowManager;

    private IToast getToast() {
        cancelToast();
        if (!isNotificationEnabled) {
            toast = new CustomToast(context);
        } else {
            toast = new SystemToast(context);
        }
        return toast;
    }

    private IToast getImgToast() {
        cancelImgToast();
        if (!isNotificationEnabled) {
            imgToast = new CustomToast(context);
        } else {
            imgToast = new SystemToast(context);
        }
        return imgToast;
    }

    private synchronized View makeTextView(String text) {
        if (textLayout == null) {
            textLayout = LayoutInflater.from(context).inflate(R.layout.dialog_toast, null);
        }

        if (textLayout.getParent() != null) {
            windowManager.removeView(textLayout);
        }

        TextView mText = (TextView) textLayout.findViewById(R.id.toast_text);
        mText.setText(text);
        return textLayout;
    }


    private synchronized View makeTextImgView(String text) {
        if (textImgLayout == null) {
            textImgLayout = LayoutInflater.from(context).inflate(R.layout.dialog_img_toast, null);
        }

        if (textImgLayout.getParent() != null) {
            windowManager.removeView(textImgLayout);
        }

        TextView mText = (TextView) textImgLayout.findViewById(R.id.toast_text);
        mText.setText(text);
        return textImgLayout;
    }

    public IToast makeToast_(String text) {
        IToast toast = getToast();

        View layout = makeTextView(text);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM, 0, 90);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.show();
        return toast;
    }

    public void makeToast(final String text) {
        if (ThreadHelper.isMainThread()) {
            makeToast_(text);
        } else {
            ThreadHelper.postMain(new Runnable() {
                @Override
                public void run() {
                    makeToast_(text);
                }
            });
        }
    }

    public void makeToast(int text) {
        makeToast(context.getString(text));
    }

    public void makeImgToast_(String text) {
        IToast toast = getImgToast();
        View layout = makeTextImgView(text);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void makeImgToast(final String text) {
        if (ThreadHelper.isMainThread()) {
            makeImgToast_(text);
        } else {
            ThreadHelper.postMain(new Runnable() {
                @Override
                public void run() {
                    makeImgToast_(text);
                }
            });
        }
    }

    public void makeImgToast(int text) {
        makeImgToast(context.getString(text));
    }


    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        textLayout = null;
    }

    public void cancelImgToast() {
        if (imgToast != null) {
            imgToast.cancel();
            imgToast = null;
        }
        textImgLayout = null;
    }
}
