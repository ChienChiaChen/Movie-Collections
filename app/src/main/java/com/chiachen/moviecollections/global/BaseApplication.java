package com.chiachen.moviecollections.global;

import android.app.Application;
import android.content.Context;

import com.chiachen.moviecollections.di.component.DaggerNetComponent;
import com.chiachen.moviecollections.di.component.NetComponent;
import com.chiachen.moviecollections.di.module.NetModule;
import com.facebook.stetho.Stetho;

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
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }
    private void initNet() {
        netComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule(this))
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
