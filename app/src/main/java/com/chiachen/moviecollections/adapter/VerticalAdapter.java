package com.chiachen.moviecollections.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.fragment.DetailFragment;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.models.Result;
import com.chiachen.moviecollections.data.network.config.BaseUrls;
import com.chiachen.moviecollections.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {
    // MoviesResponse mPopularResponse;
    ViewOnClickListener mItemOnClickListener;
    public List<Result> mResults = new ArrayList<>();

    public VerticalAdapter(MoviesResponse popularResponse) {
        // mPopularResponse = popularResponse;
    }

    public VerticalAdapter() {

    }

    public void setData(MoviesResponse data) {
        mResults.addAll(data.results);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(mResults.get(position).title);
        holder.pubDate.setText(mResults.get(position).releaseDate);

        // FIXME: 2018/05/20 Off line
        // Placeholder
        Glide.with(holder.itemView.getContext())
                .load(BaseUrls.MOVIE_IMAGE_URL + mResults.get(position).posterPath)
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOnClickListener.onClickedView(holder, holder.image, getDetailData(position));
            }

            private DetailFragment.DetailData getDetailData(int position) {
                DetailFragment.DetailData data = new DetailFragment.DetailData(
                        mResults.get(position).posterPath,
                        mResults.get(position).title,
                        mResults.get(position).overview,
                        mResults.get(position).releaseDate
                );
                return data;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (CollectionUtils.isNullOrEmpty(mResults)) {
            return 0;
        }
        return mResults.size();
    }

    public void setItemOnClickListener(ViewOnClickListener itemOnClickListener) {
        mItemOnClickListener = itemOnClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView pubDate;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_over_view);
            pubDate = itemView.findViewById(R.id.tv_release_date);
            image = itemView.findViewById(R.id.iv_movie);
        }
    }
}
