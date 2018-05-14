package com.chiachen.moviecollections.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class MoviesResponse {
    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("dates")
    @Expose
    public Dates dates;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

}
