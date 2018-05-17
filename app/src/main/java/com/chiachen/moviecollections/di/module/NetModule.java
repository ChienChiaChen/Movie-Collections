package com.chiachen.moviecollections.di.module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chiachen.moviecollections.network.ApiService;
import com.chiachen.moviecollections.network.InterceptorUtil;
import com.chiachen.moviecollections.network.config.BaseUrls;
import com.chiachen.moviecollections.network.config.HttpConfig;
import com.chiachen.moviecollections.network.exception.NoNetworkException;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

@Module
public class NetModule {
    private final Context mContext;

    public NetModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public boolean IsNetworkConnected( ) {
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
            return activityNetwork != null && activityNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    @Provides
    @Singleton
    public Interceptor provideNetworkCheckerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (IsNetworkConnected()) {
                    return chain.proceed(chain.request());
                } else {
                    throw new NoNetworkException();
                }
            }
        };
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor networkInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(InterceptorUtil.getLoggingInterceptor())
                .addInterceptor(networkInterceptor)
                .addInterceptor(new ChuckInterceptor(mContext))
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
