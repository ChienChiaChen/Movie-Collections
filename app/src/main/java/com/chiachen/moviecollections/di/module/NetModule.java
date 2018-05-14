package com.chiachen.moviecollections.di.module;

import com.chiachen.moviecollections.global.ResourceService;
import com.chiachen.moviecollections.network.ApiService;
import com.chiachen.moviecollections.network.InterceptorUtil;
import com.chiachen.moviecollections.network.config.BaseUrls;
import com.chiachen.moviecollections.network.config.HttpConfig;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtil.getLoggingInterceptor())
                .addInterceptor(new ChuckInterceptor(ResourceService.getContext()))
                .addNetworkInterceptor(InterceptorUtil.getStethoInterceptor())
                .retryOnConnectionFailure(HttpConfig.NEED_TO_RETRY)
                .writeTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(HttpConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttpClient) {
        return new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(BaseUrls.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
