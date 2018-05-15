package com.chiachen.moviecollections.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetUtil {

    public static boolean isNetworkAvailable(Context context) {
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }catch (Exception e){
            return true;
        }
    }
}

