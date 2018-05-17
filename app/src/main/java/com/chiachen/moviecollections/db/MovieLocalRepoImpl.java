package com.chiachen.moviecollections.db;

import com.chiachen.moviecollections.models.Movie;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.models.Result;
import com.chiachen.moviecollections.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

public class MovieLocalRepoImpl implements MovieLocalRepo {
    private MovieDao mMovieDao;

    public MovieLocalRepoImpl(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    public void addMovies(List<Movie> items) {
        mMovieDao.insertAll(items);
    }

    @Override
    public void addMovies(Map<Integer, MoviesResponse> items) {
        // mMovieDao.insertAll(items);
        List<Movie> movies = new ArrayList<>();
        MoviesResponse moviesResponse;
        for (Integer key : items.keySet()) {
            moviesResponse = items.get(key);
            if (null == moviesResponse || CollectionUtils.isNullOrEmpty(moviesResponse.results)) continue;

            Movie movie;
            for (Result result : moviesResponse.results) {
                movie = new Movie();
                movie.setTitle(result.title);
                movie.setOverview(result.title);
                movie.setReleaseTitle(result.releaseDate);
                movie.setImage(result.posterPath);
                movies.add(movie);
            }
        }
        // Duplicate

        addMovies(movies);
    }
}
