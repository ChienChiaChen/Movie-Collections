package com.chiachen.moviecollections.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.ArrayList;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mItems;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private MoviesResponse mPopularItems;
    private MoviesResponse mUpcomingItems;
    private ViewOnClickListener mItemOnClickListener;

    public MainAdapter() {
        mItems = new ArrayList<>();
    }

    public void setPopularItem(MoviesResponse popularItem) {
        mPopularItems = popularItem;
        mItems.add(0, mPopularItems);
    }

    public void setUpcomingItem(MoviesResponse upcomingItem) {
        mUpcomingItems = upcomingItem;
        mItems.add(1, mUpcomingItems);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder holder;

        switch (viewType) {
            case MainAdapter.HORIZONTAL:{
                view = inflater.inflate(R.layout.item_inner_horizontal, parent, false);
                holder = new HorizontalViewHolder(view);
                break;
            }
            case MainAdapter.VERTICAL:{
                view = inflater.inflate(R.layout.item_inner_vertical, parent, false);
                holder = new VerticalViewHolder(view);
                break;
            }
            default:{
                view = inflater.inflate(R.layout.item_inner_vertical, parent, false);
                holder = new VerticalViewHolder(view);
                break;
            }
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MainAdapter.VERTICAL:{
                verticalView((VerticalViewHolder) holder);
                break;
            }

            case MainAdapter.HORIZONTAL:{
                horizontalView((HorizontalViewHolder) holder);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case MainAdapter.HORIZONTAL:{
                return MainAdapter.HORIZONTAL;
            }
            case MainAdapter.VERTICAL:{
                return MainAdapter.VERTICAL;
            }
            default: {
                return -1;
            }
        }
    }

    private void verticalView(VerticalViewHolder holder) {
        VerticalAdapter adapter = new VerticalAdapter(this.mPopularItems);
        adapter.setItemOnClickListener(mItemOnClickListener);
        holder.getRecyclerView().setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.getRecyclerView().setAdapter(adapter);
    }

    private void horizontalView(HorizontalViewHolder holder) {
        HorizontalAdapter adapter = new HorizontalAdapter(this.mUpcomingItems);
        adapter.setItemOnClickListener(mItemOnClickListener);
        holder.getRecyclerView().setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.getRecyclerView().setAdapter(adapter);
    }

    public void setItemOnClickListener(ViewOnClickListener itemOnClickListener) {
        mItemOnClickListener = itemOnClickListener;
    }
}
