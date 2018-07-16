package com.chiachen.moviecollections.data.db;

import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class AppDbHelper implements DbHelper {

    @Inject MovieLocalRepo mMovieLocalRepo;

    @Inject
    public AppDbHelper() {
    }

    @Override
    public void addMovies(Map<Integer, MoviesResponse> moviesResponseMap) {
        mMovieLocalRepo.addMovies(moviesResponseMap);
    }

    @Override
    public Observable<Map<Integer, MoviesResponse>> getMovies() {
        return mMovieLocalRepo.getMovies();
    }
}
