package com.chiachen.moviecollections.presenter;

import android.support.annotation.NonNull;

import com.chiachen.moviecollections.BuildConfig;
import com.chiachen.moviecollections.adapter.MainAdapter;
import com.chiachen.moviecollections.base.BasePresenter;
import com.chiachen.moviecollections.db.MovieLocalRepo;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.network.ApiCallback;
import com.chiachen.moviecollections.network.ApiService;
import com.chiachen.moviecollections.view.MainView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private Map<Integer, MoviesResponse> mMap = new HashMap<>();
    private MovieLocalRepo mMovieLocalRepo;

    public MainPresenter setMovieLocalRepo(MovieLocalRepo movieLocalRepo) {
        mMovieLocalRepo = movieLocalRepo;
        return this;
    }

    @Inject
    public MainPresenter(MainView mainView, ApiService apiService) {
        attachView(mainView);
        mApiService = apiService;
    }

    public void loadMovie() {
        if (isViewAttached()) {
            getView().showLoading();
        }

        addSubscription(getZipObservable(), getObserver());
    }

    public Observable getZipObservable() {
        return Observable.zip(getPopularListObservable(), getUpcomingListObservable(), new BiFunction<MoviesResponse, MoviesResponse, Map<Integer, MoviesResponse>>() {
                    @Override
                    public Map<Integer, MoviesResponse> apply(MoviesResponse moviesPopularListResponse, MoviesResponse moviesUpcomingListResponse) throws Exception {
                        // mMap.put(MainAdapter.HORIZONTAL, moviesUpcomingListResponse);
                        mMap.put(MainAdapter.VERTICAL, moviesPopularListResponse);
                        return mMap;
                    }
                }).doOnNext(new Consumer<Map<Integer, MoviesResponse>>() {
                    @Override
                    public void accept(Map<Integer, MoviesResponse> moviesResponseMap) throws Exception {
                        //Save to cache;
                        mMovieLocalRepo.addMovies(moviesResponseMap);
                    }
                });
    }

    @NonNull
    private ApiCallback<Map<Integer, MoviesResponse>> getObserver() {
        return new ApiCallback<Map<Integer, MoviesResponse>>() {
            @Override
            public void onSuccess(Map<Integer, MoviesResponse> model) {
                getView().notifyAdapter(model);
            }

            @Override
            public void onFailure(String msg) {
            }

            @Override
            public void onFinish() {
                getView().hideLoading();
            }
        };
    }

    private Observable<MoviesResponse> getPopularListObservable() {
        return mApiService.getPopularMovies(BuildConfig.MOVIE_API_KEY, "en-US", "1");
    }

    private Observable<MoviesResponse> getUpcomingListObservable() {
        return mApiService.getUpcomingMovies(BuildConfig.MOVIE_API_KEY, "en-US", "1");
    }
}
