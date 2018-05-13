package com.chiachen.moviecollections.global;

import android.app.Application;
import android.content.Context;

import com.chiachen.moviecollections.di.component.DaggerNetComponent;
import com.chiachen.moviecollections.di.component.NetComponent;
import com.chiachen.moviecollections.di.module.NetModule;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class BaseApplication extends Application {

    private NetComponent netComponent;
    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        initResource();
        initNet();
    }

    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }
    private void initNet() {
        netComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public static BaseApplication getInstance() {
        return sInstance;
    }

    private void initResource() {
        ResourceService.init(getApplicationContext());
    }
}
