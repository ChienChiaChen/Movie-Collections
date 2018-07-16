package com.chiachen.moviecollections.presenter;

import android.support.annotation.NonNull;

import com.chiachen.moviecollections.BuildConfig;
import com.chiachen.moviecollections.adapter.MainAdapter;
import com.chiachen.moviecollections.base.BasePresenter;
import com.chiachen.moviecollections.data.DataManager;
import com.chiachen.moviecollections.data.network.ApiCallback;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.utils.rx.SchedulerProvider;
import com.chiachen.moviecollections.view.MainView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class MainPresenter extends BasePresenter<MainView> {
    private Map<Integer, MoviesResponse> mMap = new HashMap<>();
    // private MovieLocalRepo mMovieLocalRepo;

    @Inject
    public MainPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    public void loadMovie(int pageNumber) {
        if (isViewAttached()) {
            getView().showRefreshing();
        }

        addSubscription(getDataFromRemote(pageNumber), getObserver());
    }

    private Observable<Map<Integer, MoviesResponse>> getDataFromLocal() {
        return getDataManager().getMovies();
    }

    private Observable getDataFromRemote(int pageNumber) {
        return Observable
                .zip(getPopularListObservable(pageNumber), getUpcomingListObservable(), new BiFunction<MoviesResponse, MoviesResponse, Map<Integer, MoviesResponse>>() {
                    @Override
                    public Map<Integer, MoviesResponse> apply(MoviesResponse moviesPopularListResponse, MoviesResponse moviesUpcomingListResponse) throws Exception {
                        mMap.put(MainAdapter.VERTICAL, moviesPopularListResponse);
                        return mMap;
                    }
                })
                .doOnNext(new Consumer<Map<Integer, MoviesResponse>>() {
                    @Override
                    public void accept(Map<Integer, MoviesResponse> moviesResponseMap) throws Exception {
                        //Save to cache;
                        getDataManager().addMovies(moviesResponseMap);
                    }
                })
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .onErrorResumeNext(new Function<Throwable, ObservableSource< Map<Integer, MoviesResponse>>>() {
                    @Override
                    public ObservableSource< Map<Integer, MoviesResponse>> apply(Throwable throwable) throws Exception {
                        return getDataFromLocal();
                    }
                });
    }

    @NonNull
    private ApiCallback<Map<Integer, MoviesResponse>> getObserver() {
        return new ApiCallback<Map<Integer, MoviesResponse>>(getView()) {
            @Override
            public void onSuccess(Map<Integer, MoviesResponse> model) {
                getView().notifyAdapter(model);
            }
        };
    }

    private Observable<MoviesResponse> getPopularListObservable(int pageNumber) {
        return getDataManager().getPopularMovies(BuildConfig.MOVIE_API_KEY, "en-US", String.valueOf(pageNumber));
    }

    private Observable<MoviesResponse> getUpcomingListObservable() {
        return getDataManager().getUpcomingMovies(BuildConfig.MOVIE_API_KEY, "en-US", "1");
    }
}
