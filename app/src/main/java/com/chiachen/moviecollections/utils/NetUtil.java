package com.chiachen.moviecollections.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
            return activityNetwork != null && activityNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}

