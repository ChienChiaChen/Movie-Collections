package com.chiachen.moviecollections.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.chiachen.moviecollections.db.DBConfiguration;
import com.chiachen.moviecollections.db.LocalDB;
import com.chiachen.moviecollections.db.MovieLocalRepo;
import com.chiachen.moviecollections.db.MovieLocalRepoImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Context provideApplication() {
        return mApplication;
    }

    @Provides
    LocalDB provideLocalDatabase() {
        return Room.databaseBuilder(mApplication.getApplicationContext(), LocalDB.class, DBConfiguration.DB_NAME).build();
    }

    @Provides
    MovieLocalRepo provideMovieLocalRepo(LocalDB localDB) {
        return new MovieLocalRepoImpl(localDB.movieDao());
    }

}
