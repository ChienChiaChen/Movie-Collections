package com.chiachen.moviecollections.data.db;

import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by jianjiacheng on 2018/7/10.
 */

public interface DbHelper {
    void addMovies(Map<Integer, MoviesResponse> moviesResponseMap);

    Observable<Map<Integer,MoviesResponse>> getMovies();
}
