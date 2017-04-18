package com.lenny.framedemo.common.image;

import android.graphics.Bitmap;
import android.view.View;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public interface ImageLoadListener {
    /*
    加载失败
     */
    void onLoadFail(String url, View view, Throwable throwable);

    /*
    加载成功
     */
    void onLoadSucess(String url, View view, Bitmap bitmap);

    void onProgressUpdate(int current, int total);
}
