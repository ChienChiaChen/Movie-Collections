package com.chiachen.moviecollections.data.network;

import com.chiachen.moviecollections.models.MoviesResponse;

import io.reactivex.Observable;

/**
 * Created by jianjiacheng on 2018/7/10.
 */

public interface ApiHelper {
    Observable<MoviesResponse> getPopularMovies(String movieApiKey, String lang, String pageIndex);
    Observable<MoviesResponse> getUpcomingMovies(String movieApiKey, String lang, String pageIndex);
}
