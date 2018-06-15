package com.chiachen.moviecollections.di.module;

import com.chiachen.moviecollections.activity.MainActivity;
import com.chiachen.moviecollections.db.MovieLocalRepo;
import com.chiachen.moviecollections.network.ApiService;
import com.chiachen.moviecollections.presenter.MainPresenter;
import com.chiachen.moviecollections.view.MainView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class MainModule {

    @Binds
    abstract MainView provideMainView(MainActivity mainActivity);

    @Provides
    static MainPresenter provideMainPresenter(MainView mainView, ApiService apiService, MovieLocalRepo movieLocalRepo) {
        return new MainPresenter(mainView, apiService, movieLocalRepo);
    }
}
