package com.lenny.framedemo.compent.helper;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lenny.framedemo.common.image.DisplayOption;
import com.lenny.framedemo.common.image.ImageDisplayLoader;
import com.lenny.framedemo.common.image.ImageLoadListener;

import java.io.File;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public class ImageHelper {
    private static ImageDisplayLoader sLoader;

    static {
        sLoader = CDIHelper.getInstance().mImageDisplayLoader;
    }

    public static void display(ImageView imageView, String url, int defaultImage) {
        DisplayOption displayOption = null;
        if (displayOption == null) {
            displayOption = DisplayOption.builder().setDefaultResId(defaultImage);
        }
        sLoader.display(imageView, url, null, displayOption);
    }

    public static void display(ImageView imageView, String url) {
        sLoader.display(imageView, url, null, null);

    }

    public static void display(ImageView imageView, String url, ImageLoadListener listener) {
        sLoader.display(imageView, url, listener, null);

    }

    public static void display(ImageView imageView, String url, DisplayOption option) {
        sLoader.display(imageView, url, null, option);

    }

    public static Bitmap syncLoad(File file) {
        return sLoader.syncLoad(file);
    }

    public static Bitmap syncLoad(String url) {
        return sLoader.syncLoad(url);

    }

    public static void pause() {
        sLoader.pause();
    }

    public static void resume() {
        sLoader.resume();
    }

    public static void clearCache(int... caches) {
        sLoader.clearCache(caches);
    }
}
