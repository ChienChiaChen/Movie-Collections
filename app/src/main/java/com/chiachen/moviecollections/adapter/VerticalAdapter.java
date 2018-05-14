package com.chiachen.moviecollections.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.network.config.BaseUrls;


public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {
    MoviesResponse mPopularResponse;

    public VerticalAdapter(MoviesResponse popularResponse) {
        mPopularResponse = popularResponse;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mPopularResponse.results.get(position).title);
        holder.description.setText(mPopularResponse.results.get(position).overview);
        holder.pubDate.setText(mPopularResponse.results.get(position).releaseDate);

        Glide.with(holder.itemView.getContext())
                .load(BaseUrls.MOVIE_IMAGE_URL + mPopularResponse.results.get(position).posterPath)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mPopularResponse.results.size();
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
