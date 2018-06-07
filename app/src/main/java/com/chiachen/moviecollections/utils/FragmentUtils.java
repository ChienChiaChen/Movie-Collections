package com.chiachen.moviecollections.utils;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.chiachen.moviecollections.R;


public class FragmentUtils {
    private FragmentUtils() {
    }

    public static void nextFragment(FragmentManager fragmentManager,
                                    Fragment fragment,
                                    String tag,
                                    @IdRes int containerViewId)
    {
        fragmentManager
                .beginTransaction()
                .addToBackStack(tag)
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .add(containerViewId, fragment, tag)
                .commit();
    }
}
