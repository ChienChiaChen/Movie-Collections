package com.chiachen.moviecollections.di.component;

import com.chiachen.moviecollections.di.module.NetModule;
import com.chiachen.moviecollections.network.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by jianjiacheng on 14/05/2018.
 */


@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkHttp();
    Retrofit getRetrofit();
}
