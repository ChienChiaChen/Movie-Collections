package com.chiachen.moviecollections.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class Dates {
    @SerializedName("maximum")
    @Expose
    public String maximum;
    @SerializedName("minimum")
    @Expose
    public String minimum;
}

