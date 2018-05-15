package com.chiachen.moviecollections.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.base.MVPFragment;
import com.chiachen.moviecollections.network.config.BaseUrls;

/**
 * Created by jianjiacheng on 15/05/2018.
 */

public class DetailFragment extends MVPFragment {

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_OVERVIEW = "arg_overview";
    private static final String ARG_RELEASE_DATE = "arg_release_date";
    private static final String ARG_IMAGE = "arg_image";

    ImageView mImage;
    TextView mTitle;
    TextView mOverview;

    public static DetailFragment newInstance(DetailData detailData) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, detailData.getTitle());
        bundle.putString(ARG_OVERVIEW, detailData.getOverview());
        bundle.putString(ARG_RELEASE_DATE, detailData.getDate());
        bundle.putString(ARG_IMAGE, detailData.getImage());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    protected void initUI() {
        Log.e("JASON_CHIEN", "\nFragment init UI");
        mTitle = mBaseView.findViewById(R.id.detail_head);
        mOverview = mBaseView.findViewById(R.id.detail_body);
        mImage = mBaseView.findViewById(R.id.detail_image);

        mTitle.setText(getArguments().getString(ARG_TITLE));
        mOverview.setText(getArguments().getString(ARG_OVERVIEW));
        Glide.with(mBaseView.getContext())
                .load(BaseUrls.MOVIE_IMAGE_URL + getArguments().getString(ARG_IMAGE))
                .into(mImage);
    }

    @Override
    public boolean onBackClick() {
        if (this.isVisible()) {
            // getFragmentManager().beginTransaction().remove(this).commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static class DetailData {
        private String mImage;
        private String mTitle;
        private String mOverview;
        private String mDate;

        public DetailData(String image, String title, String overview, String date) {
            mImage = image;
            mTitle = title;
            mOverview = overview;
            mDate = date;
        }

        public String getImage() {
            return mImage;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getOverview() {
            return mOverview;
        }
        public String getDate() {
            return mDate;
        }

    }
}
