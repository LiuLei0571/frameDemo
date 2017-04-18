package com.lenny.framedemo.compent.cdi.cmp;

import android.content.Context;

import com.lenny.framedemo.common.event.IEvent;
import com.lenny.framedemo.common.event.impl.EventBusImpl;

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

    public AppModule(Context context) {
        mContext = context;
    }
    @Provides
    @Singleton
    protected IEvent provideEvenBus(){
        return new EventBusImpl(de.greenrobot.event.EventBus.getDefault());
    }
}
