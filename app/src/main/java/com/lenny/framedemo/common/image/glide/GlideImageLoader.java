package com.lenny.framedemo.common.image.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lenny.framedemo.R;
import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.common.image.DisplayOption;
import com.lenny.framedemo.common.image.ImageDisplayLoader;
import com.lenny.framedemo.common.image.ImageLoadListener;
import com.lenny.framedemo.common.image.glide.module.GlideCircleTransform;
import com.lenny.framedemo.common.image.glide.module.GlideRoundTransform;

import java.io.File;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public class GlideImageLoader implements ImageDisplayLoader {
    private Context mContext;

    public GlideImageLoader(Context context) {
        mContext = context;
    }

    @Override
    public void display(ImageView imageView, String url, ImageLoadListener listener, DisplayOption option, boolean round, boolean circle) {
        if (imageView != null) {
            DrawableTypeRequest<String> drawableTypeRequest = Glide.with(mContext).load(url);
            if (option != null) {
                if (option.cacheMemory != null) {
                    drawableTypeRequest.skipMemoryCache(!option.cacheMemory);
                }
                if (option.cacheOnDisk != null) {
                    if (option.cacheOnDisk) {
                        drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE);
                    } else {
                        drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
                    }
                }
                if (option.errorResId != null) {
                    drawableTypeRequest.error(option.errorResId);
                }
                if (option.maxWith != null && option.maxHeight != null) {
                    drawableTypeRequest.override(option.maxWith, option.maxHeight);
                }
                if (option.defaultResId != -1) {
                    drawableTypeRequest.placeholder(option.defaultResId);
                }
                if (option.loadingResId != -1) {
                    drawableTypeRequest.placeholder(option.loadingResId);
                }
            }
            if (listener != null) {
                drawableTypeRequest.listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        listener.onLoadFail(url, imageView, e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (resource instanceof GlideDrawable) {
                            GlideBitmapDrawable resourcel = (GlideBitmapDrawable) resource;
                            listener.onLoadSucess(url, imageView, null);
                        }
                        return false;
                    }
                });
            }
            if (round) {
                drawableTypeRequest.transform(new GlideCircleTransform(mContext));
            }
            if (circle) {
                drawableTypeRequest.transform(new GlideRoundTransform(mContext));
            }
            drawableTypeRequest.thumbnail(0.4f).dontAnimate().into(imageView);
        }
    }

    @Override
    public Bitmap syncLoad(File file) {
        try {
            return Glide.with(mContext).load(file).asBitmap().dontAnimate().placeholder(R.drawable.ic_default).thumbnail(0.4f).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public Bitmap syncLoad(String url) {
        try {
            return Glide.with(mContext).load(url).asBitmap().dontAnimate().placeholder(R.drawable.ic_default).thumbnail(0.4f).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public void clearCache(int... cachePress) {
        if (cachePress != null) {
            for (int cache : cachePress) {
                switch (cache) {
                    case ImageDisplayLoader.CACHE_IN_DISK:
                        if (ThreadHelper.isMainThread()) {
                            ThreadHelper.postMain(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.get(mContext).clearDiskCache();

                                }
                            });
                        } else {
                            Glide.get(mContext).clearDiskCache();

                        }
                        break;
                    case ImageDisplayLoader.CACHE_IN_MEN:
                        Glide.get(mContext).clearMemory();//清理内存缓存
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void pause() {
        Glide.with(mContext).pauseRequests();
    }

    @Override
    public void resume() {
        Glide.with(mContext).resumeRequests();
    }

    @Override
    public void preLoad(String url) {
        if (!ThreadHelper.isMainThread()) {
            ThreadHelper.postMain(new Runnable() {
                @Override
                public void run() {
                    Glide.with(mContext).load(url).asBitmap().placeholder(R.drawable.ic_default).preload(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                }
            });
        } else {
            Glide.with(mContext).load(url).asBitmap().placeholder(R.drawable.ic_default).preload(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

        }
    }
}
