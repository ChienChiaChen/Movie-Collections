package com.chiachen.moviecollections.db;

import com.chiachen.moviecollections.models.Movie;
import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

public interface MovieLocalRepo {
    void addMovies(List<Movie> items);
    void addMovies(Map<Integer, MoviesResponse> items);
}
