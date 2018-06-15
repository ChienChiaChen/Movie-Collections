package com.chiachen.moviecollections.di.component;

import com.chiachen.moviecollections.activity.MainActivity;
import com.chiachen.moviecollections.di.module.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jianjiacheng on 2018/6/14.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity getMainActivity();
}

