package com.chiachen.moviecollections.base;

import android.support.annotation.NonNull;

import com.chiachen.moviecollections.data.DataManager;
import com.chiachen.moviecollections.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


public class BasePresenter<V> {
    private WeakReference<V> mView;
    protected SchedulerProvider mSchedulerProvider;
    protected CompositeDisposable mCompositeDisposable;
    protected DataManager mDataManager;

    @Inject
    public BasePresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = compositeDisposable;
        mDataManager = dataManager;
    }

    public void attachView(@NonNull V view) {
        mView = new WeakReference<>(view);
    }

    public boolean isViewAttached() {
        return null != mView && null != mView.get();
    }

    public V getView() {
        return this.isViewAttached() ? mView.get():null;
    }

    public void detachView() {
        this.mView = null;
        onUnSubscribe();
    }

    private void onUnSubscribe() {
        if (null == mCompositeDisposable) return;
        mCompositeDisposable.dispose();
    }

    public void addSubscription(Observable observable, DisposableObserver observer) {
        mCompositeDisposable.add(observer);
        observable.subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribeWith(observer);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
