package com.chiachen.moviecollections.data.network;

import com.chiachen.moviecollections.models.MoviesResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by jianjiacheng on 2018/7/10.
 */


public class AppApiHelper implements ApiHelper {

    @Inject ApiService mApiService;

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<MoviesResponse> getPopularMovies(String movieApiKey, String lang, String pageIndex) {
        return mApiService.getPopularMovies(movieApiKey, lang, pageIndex);
    }

    @Override
    public Observable<MoviesResponse> getUpcomingMovies(String movieApiKey, String lang, String pageIndex) {
        return  mApiService.getPopularMovies(movieApiKey, lang, pageIndex);
    }
}
