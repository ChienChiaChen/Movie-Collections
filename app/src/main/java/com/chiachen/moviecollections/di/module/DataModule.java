package com.chiachen.moviecollections.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.chiachen.moviecollections.data.db.AppDbHelper;
import com.chiachen.moviecollections.data.db.DBConfiguration;
import com.chiachen.moviecollections.data.db.DbHelper;
import com.chiachen.moviecollections.data.db.LocalDB;
import com.chiachen.moviecollections.data.db.MovieLocalRepo;
import com.chiachen.moviecollections.data.db.MovieLocalRepoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataModule {
    @Singleton
    @Provides
    LocalDB provideLocalDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                LocalDB.class,
                DBConfiguration.DB_NAME)
                .build();
    }

    @Singleton
    @Provides
    MovieLocalRepo provideMovieLocalRepo(LocalDB localDB) {
        return new MovieLocalRepoImpl(localDB.movieDao());
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }
}
