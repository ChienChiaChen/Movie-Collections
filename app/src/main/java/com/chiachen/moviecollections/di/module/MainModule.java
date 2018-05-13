package com.chiachen.moviecollections.di.module;

import com.chiachen.moviecollections.view.MainView;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {
    private final MainView mView;

    public MainModule(MainView view) {
        this.mView = view;
    }

    @Provides
    public MainView provideMainView() {
        return mView;
    }
}
