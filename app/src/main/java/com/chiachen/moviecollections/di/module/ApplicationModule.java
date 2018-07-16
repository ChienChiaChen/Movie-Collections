package com.chiachen.moviecollections.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.chiachen.moviecollections.data.AppDataManager;
import com.chiachen.moviecollections.data.DataManager;
import com.chiachen.moviecollections.data.network.ApiHelper;
import com.chiachen.moviecollections.data.network.AppApiHelper;
import com.chiachen.moviecollections.data.prefs.AppPrefHelper;
import com.chiachen.moviecollections.data.prefs.PrefConfig;
import com.chiachen.moviecollections.data.prefs.PrefHelper;
import com.chiachen.moviecollections.utils.rx.AppSchedulerProvider;
import com.chiachen.moviecollections.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    String providePreferenceName() {
        return PrefConfig.PREF_NAME;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context, String prefFileName) {
        return context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PrefHelper providePreferencesHelper(AppPrefHelper appPrefHelper) {
        return appPrefHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
