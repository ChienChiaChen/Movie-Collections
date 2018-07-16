package com.chiachen.moviecollections.di.component;

import android.content.Context;

import com.chiachen.moviecollections.di.module.ApplicationModule;
import com.chiachen.moviecollections.di.module.DataModule;
import com.chiachen.moviecollections.di.module.NetModule;
import com.chiachen.moviecollections.global.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by jianjiacheng on 2018/05/17.
 */


@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        ActivityBuilder.class,
        NetModule.class,
        DataModule.class
})
@Singleton
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Context context);
        ApplicationComponent build();
    }

    void inject(Context context);
}
