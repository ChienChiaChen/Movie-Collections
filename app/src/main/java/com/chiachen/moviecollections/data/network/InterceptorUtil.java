package com.chiachen.moviecollections.data.network;

import com.chiachen.moviecollections.BuildConfig;
import com.chiachen.moviecollections.data.network.config.HttpConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

/**
 * Created by jianjiacheng on 16/03/2018.
 */

public class InterceptorUtil {
    public static LoggingInterceptor getLoggingInterceptor() {
        return (new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(BuildConfig.DEBUG ? Level.BASIC : Level.NONE)
                .log(Platform.INFO)
                .request(HttpConfig.TAG_LOG_REQUEST)
                .response(HttpConfig.TAG_LOG_RESPONSE)
                .addHeader(HttpConfig.TAG_LOG_HEADER, BuildConfig.VERSION_NAME)
                .build());
    }

    public static StethoInterceptor getStethoInterceptor() {
        return new StethoInterceptor();
    }

    public static Interceptor HeaderInterceptor() {
        return  new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request mRequest = chain.request(); // For token
                    return chain.proceed(mRequest);
                }
            };
    }

}
