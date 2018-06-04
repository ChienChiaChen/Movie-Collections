package com.chiachen.moviecollections.network;

import android.util.Log;

import com.chiachen.moviecollections.network.exception.NoNetworkException;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public abstract class ApiCallback<M> extends DisposableObserver<M> {
    public static final String NETWORK_ERROR = "Network error";
    public static final String SERVER_ERROR = "Server error";

    public abstract void onSuccess(M model);
    public abstract void onFailure(String msg);
    public abstract void onFinish();

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NoNetworkException) {
            Log.d("JASON_CHIEN", "\nNoNetworkException");
            onFailure("Network is unavailable");
        }else if (e instanceof HttpException) {
            Log.d("JASON_CHIEN", "\n");
            HttpException httpException = (HttpException) e;

            int errCode = httpException.code();
            String errMsg = httpException.message();

            if (HttpsURLConnection.HTTP_GATEWAY_TIMEOUT == errCode) {
                errMsg = ApiCallback.NETWORK_ERROR;
            } else if (HttpsURLConnection.HTTP_BAD_GATEWAY == errCode || HttpsURLConnection.HTTP_NOT_FOUND == errCode) {
                errMsg = ApiCallback.SERVER_ERROR;
            }

            onFailure(errMsg);
        } else {
            Log.d("JASON_CHIEN", "\ngetMessage");
            onFailure(e.getMessage());
        }

        onFinish();
    }
}
