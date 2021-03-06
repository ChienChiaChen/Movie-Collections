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
import com.chiachen.moviecollections.data.network.config.BaseUrls;


public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private MoviesResponse mUpcomingResponse;
    private ViewOnClickListener mItemOnClickListener;

    public HorizontalAdapter(MoviesResponse data) {
        mUpcomingResponse = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(mUpcomingResponse.results.get(position).title);
        // holder.description.setText(mUpcomingResponse.results.get(position).overview);
        holder.pubDate.setText(mUpcomingResponse.results.get(position).releaseDate);

        // FIXME: 2018/05/20 Off line
        // Placeholder
        Glide.with(holder.itemView.getContext())
                .load(BaseUrls.MOVIE_IMAGE_URL + mUpcomingResponse.results.get(position).posterPath)
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOnClickListener.onClickedView(holder, holder.image, getDetailData(position));
            }

            private DetailFragment.DetailData getDetailData(int position) {
                return new DetailFragment.DetailData(
                        mUpcomingResponse.results.get(position).posterPath,
                        mUpcomingResponse.results.get(position).title,
                        mUpcomingResponse.results.get(position).overview,
                        mUpcomingResponse.results.get(position).releaseDate
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUpcomingResponse.results.size();
    }

    public void setItemOnClickListener(ViewOnClickListener itemOnClickListener) {
        mItemOnClickListener = itemOnClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView pubDate;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_over_view);
            pubDate = itemView.findViewById(R.id.tv_release_date);
            image = itemView.findViewById(R.id.iv_movie);
        }
    }
}
