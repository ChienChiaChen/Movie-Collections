package com.chiachen.moviecollections.di.component;

import com.chiachen.moviecollections.activity.MainActivity;
import com.chiachen.moviecollections.di.UserScope;
import com.chiachen.moviecollections.di.module.MainModule;

import dagger.Component;


@UserScope
@Component(modules = MainModule.class, dependencies = {NetComponent.class, ApplicationComponent.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
