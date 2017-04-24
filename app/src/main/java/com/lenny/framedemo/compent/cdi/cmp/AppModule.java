package com.lenny.framedemo.compent.cdi.cmp;

import android.content.Context;
import android.content.res.AssetManager;

import com.lenny.framedemo.common.event.IEvent;
import com.lenny.framedemo.common.event.impl.EventBusImpl;
import com.lenny.framedemo.common.http.HttpResultParse;
import com.lenny.framedemo.common.http.HttpScheduler;
import com.lenny.framedemo.common.http.IRequestListener;
import com.lenny.framedemo.common.http.impl.okhttp3.CookiesManager;
import com.lenny.framedemo.common.http.impl.okhttp3.ICookieStore;
import com.lenny.framedemo.common.http.impl.okhttp3.OkHttpSchedule;
import com.lenny.framedemo.common.image.ImageDisplayLoader;
import com.lenny.framedemo.common.image.glide.GlideImageLoader;
import com.lenny.framedemo.common.parse.IParse;
import com.lenny.framedemo.common.parse.impl.FastJsonParse;
import com.lenny.framedemo.compent.constants.Configs;
import com.lenny.framedemo.compent.http.PersistentCookieStoreNew;
import com.lenny.framedemo.compent.ui.AppToast;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

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

    @Provides
    @Singleton
    protected HttpScheduler provideHttpSchuduler3(OkHttpClient okHttpClient, IRequestListener iRequestListener, HttpResultParse resultParse) {
        OkHttpSchedule schedule = new OkHttpSchedule(okHttpClient, resultParse);
        if (!Configs.RELEASE) {
            schedule.setIRequestListener(iRequestListener);
        }
        return schedule;
    }

    @Provides
    @Singleton
    protected OkHttpClient provideOkHttpClient3(AssetManager assetManager, CookiesManager cookiesManager) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .cookieJar(cookiesManager);
        return builder.build();
    }
}
