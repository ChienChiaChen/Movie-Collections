package com.chiachen.moviecollections.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chiachen.moviecollections.R;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

class HorizontalViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView mRecyclerView;

    public HorizontalViewHolder(View view) {
        super(view);
        mRecyclerView = itemView.findViewById(R.id.inner_recyclerView);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
