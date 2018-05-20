package com.chiachen.moviecollections.db;

import com.chiachen.moviecollections.adapter.MainAdapter;
import com.chiachen.moviecollections.models.Movie;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.models.Result;
import com.chiachen.moviecollections.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

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

        addMovies(movies);
    }

    @Override
    public Observable<Map<Integer, MoviesResponse>> getMovies() {
        return Observable.fromCallable(new Callable<Map<Integer, MoviesResponse>>() {
                @Override
                public Map<Integer, MoviesResponse> call() throws Exception {
                    List<Movie> movies = mMovieDao.getAll();
                    Map<Integer, MoviesResponse> moviesResponseMap = new HashMap<>();
                    MoviesResponse moviesResponse = new MoviesResponse();
                    Result result;
                    for (Movie movie : movies) {
                        result = new Result();
                        result.title = movie.getTitle();
                        result.overview = movie.getOverview();
                        result.posterPath = movie.getImage();
                        result.releaseDate = movie.getReleaseTitle();
                        moviesResponse.addResult(result);
                    }
                    moviesResponseMap.put(
                            MainAdapter.VERTICAL,
                            moviesResponse
                    );
                    return moviesResponseMap;
            }
        });
    }
}
