package com.chiachen.moviecollections.di.module;

import com.chiachen.moviecollections.activity.MainActivity;
import com.chiachen.moviecollections.data.DataManager;
import com.chiachen.moviecollections.presenter.MainPresenter;
import com.chiachen.moviecollections.utils.rx.SchedulerProvider;
import com.chiachen.moviecollections.view.MainView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public abstract class MainModule {

    @Binds
    abstract MainView provideMainView(MainActivity mainActivity);

    @Provides
    static MainPresenter provideMainPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        return new MainPresenter(schedulerProvider, compositeDisposable, dataManager);
    }
}
