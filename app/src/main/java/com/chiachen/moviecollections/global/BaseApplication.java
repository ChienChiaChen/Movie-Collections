package com.chiachen.moviecollections.global;

import android.app.Application;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initResource();
    }

    private void initResource() {
        ResourceService.init(getApplicationContext());
    }
}
