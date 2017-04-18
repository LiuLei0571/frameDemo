package com.lenny.framedemo.common.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public interface ImageDisplayLoader {
    int CACHE_IN_MEN = 1;
    int CACHE_IN_DISK = 2;

    void display(ImageView imageView, String url, ImageLoadListener listener, DisplayOption option);

    Bitmap syncLoad(File file);

    Bitmap syncLoad(String url);

    void clearCache(int... cachePress);

    void pause();

    void resume();

    void preLoad(String url);
}
