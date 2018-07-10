package com.chiachen.moviecollections.data.network;

import com.chiachen.moviecollections.models.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public interface ApiService {

    @GET("/3/movie/popular")
    Observable<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey,
                                                @Query("language")String lang,
                                                @Query("page")String pageIndex);

    @GET("/3/movie/upcoming")
    Observable<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                                 @Query("language")String lang,
                                                 @Query("page")String pageIndex);

    @GET("/3/search/movie")
    Observable<MoviesResponse> searchMovies(@Query("api_key") String apiKey,
                                            @Query("query") String query);

}
