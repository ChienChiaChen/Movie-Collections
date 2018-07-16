package com.chiachen.moviecollections.data;

import com.chiachen.moviecollections.data.db.DbHelper;
import com.chiachen.moviecollections.data.network.ApiHelper;
import com.chiachen.moviecollections.data.prefs.PrefHelper;
import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {
    @Inject DbHelper mDbHelper;
    @Inject ApiHelper mApiHelper;
    @Inject PrefHelper mPrefHelper;

    @Inject
    public AppDataManager() {
    }


    @Override
    public Observable<MoviesResponse> getPopularMovies(String movieApiKey, String lang, String pageIndex) {
        return mApiHelper.getPopularMovies(movieApiKey, lang, pageIndex);
    }

    @Override
    public Observable<MoviesResponse> getUpcomingMovies(String movieApiKey, String lang, String pageIndex) {
        return mApiHelper.getUpcomingMovies(movieApiKey, lang, pageIndex);
    }

    @Override
    public void addMovies(Map<Integer, MoviesResponse> moviesResponseMap) {
        mDbHelper.addMovies(moviesResponseMap);
    }

    @Override
    public Observable<Map<Integer, MoviesResponse>> getMovies() {
        return mDbHelper.getMovies();
    }
}
