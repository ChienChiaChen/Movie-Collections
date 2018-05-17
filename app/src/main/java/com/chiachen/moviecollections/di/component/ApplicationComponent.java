package com.chiachen.moviecollections.di.component;

import com.chiachen.moviecollections.db.MovieLocalRepo;
import com.chiachen.moviecollections.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by jianjiacheng on 2018/05/17.
 */


@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    MovieLocalRepo getMovieLocalRepo();
}
