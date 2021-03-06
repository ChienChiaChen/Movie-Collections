package com.chiachen.moviecollections.data.db;

import com.chiachen.moviecollections.models.Movie;
import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

public interface MovieLocalRepo {
    void addMovies(List<Movie> items);
    void addMovies(Map<Integer, MoviesResponse> items);

    Observable<Map<Integer, MoviesResponse>> getMovies();
}
