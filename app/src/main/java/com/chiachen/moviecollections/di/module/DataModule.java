package com.chiachen.moviecollections.di.module;

import android.arch.persistence.room.Room;

import com.chiachen.moviecollections.data.db.DBConfiguration;
import com.chiachen.moviecollections.data.db.LocalDB;
import com.chiachen.moviecollections.data.db.MovieLocalRepo;
import com.chiachen.moviecollections.data.db.MovieLocalRepoImpl;
import com.chiachen.moviecollections.global.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataModule {
    @Singleton
    @Provides
    LocalDB provideLocalDatabase(BaseApplication baseApplication) {
        return Room.databaseBuilder(
                baseApplication.getApplicationContext(),
                LocalDB.class,
                DBConfiguration.DB_NAME)
                .build();
    }

    @Singleton
    @Provides
    MovieLocalRepo provideMovieLocalRepo(LocalDB localDB) {
        return new MovieLocalRepoImpl(localDB.movieDao());
    }
}
