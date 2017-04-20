package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.compent.base.BaseActivity;
import com.lenny.framedemo.compent.ui.widget.Progress;


/**
 * 用途：
 * Created by milk on 16/8/27.
 * 邮箱：649444395@qq.com
 */
public class LoadingHelper {
    public static void showLoading(BaseActivity fragmentActivity) {
        Progress.show(fragmentActivity, null);
    }

    public static void showLoading(BaseActivity fragmentActivity, String content) {
        Progress.show(fragmentActivity, content);
    }

    public static void dismiss() {
        Progress.dismiss();
    }
}
