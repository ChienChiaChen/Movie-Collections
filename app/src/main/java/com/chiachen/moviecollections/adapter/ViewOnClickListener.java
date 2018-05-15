package com.chiachen.moviecollections.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chiachen.moviecollections.fragment.DetailFragment;

/**
 * Created by jianjiacheng on 15/05/2018.
 */

public interface ViewOnClickListener {
    void onClickedView(RecyclerView.ViewHolder holder, View transitionView, DetailFragment.DetailData detailData);
}
