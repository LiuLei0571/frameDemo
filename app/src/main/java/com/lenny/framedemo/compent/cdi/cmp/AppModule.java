package com.lenny.framedemo.compent.cdi.cmp;

import android.content.Context;

import com.lenny.framedemo.common.event.IEvent;
import com.lenny.framedemo.common.event.impl.EventBusImpl;
import com.lenny.framedemo.common.http.impl.okhttp3.CookiesManager;
import com.lenny.framedemo.common.http.impl.okhttp3.ICookieStore;
import com.lenny.framedemo.common.image.ImageDisplayLoader;
import com.lenny.framedemo.common.image.glide.GlideImageLoader;
import com.lenny.framedemo.common.parse.IParse;
import com.lenny.framedemo.common.parse.impl.FastJsonParse;
import com.lenny.framedemo.compent.http.PersistentCookieStoreNew;
import com.lenny.framedemo.compent.ui.AppToast;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */
@Module
public class AppModule {
    private final Context mContext;

//    @Provides
//    @Singleton
//    protected

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    protected IEvent provideEvenBus() {
        return new EventBusImpl(de.greenrobot.event.EventBus.getDefault());
    }

    @Provides
    @Singleton
    protected IParse provideFastJson() {
        return new FastJsonParse();
    }

    @Provides
    @Singleton
    protected ImageDisplayLoader provideImageLoader() {
        GlideImageLoader imageLoader = new GlideImageLoader(mContext);
        return imageLoader;
    }

    @Provides
    @Singleton
    protected AppToast provideToast() {
        return new AppToast(mContext);
    }

    @Provides
    @Singleton
    protected ICookieStore provideICookieStore() {
        PersistentCookieStoreNew cookieStoreNew = new PersistentCookieStoreNew();
        return cookieStoreNew;
    }

    @Provides
    @Singleton
    protected CookiesManager provideCookiesManager(CookieManager webCookieManager, ICookieStore cookieStore) {
        return new CookiesManager(cookieStore, webCookieManager);
    }

    @Provides
    @Singleton
    protected CookieManager provideCookieManager() {
        CookieSyncManager.createInstance(mContext);
        CookieManager webCookieManager = CookieManager.getInstance();
        webCookieManager.setAcceptCookie(true);
        return webCookieManager;
    }
}
