package com.chiachen.moviecollections.data.prefs;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by jianjiacheng on 2018/7/10.
 */

public class AppPrefHelper implements PrefHelper {

    @Inject
    SharedPreferences mPrefs;

    @Inject
    public AppPrefHelper() {
    }
}
