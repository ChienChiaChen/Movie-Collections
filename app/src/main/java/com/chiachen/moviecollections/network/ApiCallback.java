package com.chiachen.moviecollections.network;

import com.chiachen.moviecollections.base.BaseView;
import com.chiachen.moviecollections.network.exception.NoNetworkException;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public abstract class ApiCallback<M> extends DisposableObserver<M> {
    private WeakReference<BaseView> mWeakReference;
    public abstract void onSuccess(M model);

    public ApiCallback(BaseView baseView) {
        this.mWeakReference = new WeakReference<>(baseView);
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
        BaseView view = mWeakReference.get();
        if (null != view) {
            view.dismissProgressDialog();
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        BaseView view = mWeakReference.get();
        if (e instanceof NoNetworkException) {
            view.NoNetworkException();
        } else  if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException)e).response().errorBody();
            view.onUnknownError(getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            view.onTimeout();
        } else if (e instanceof IOException) {
            view.onNetworkError();
        } else {
            view.onUnknownError(e.getMessage());
        }
        view.dismissProgressDialog();
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
