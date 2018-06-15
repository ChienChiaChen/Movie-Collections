package com.chiachen.moviecollections.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.fragment.DetailFragment;
import com.chiachen.moviecollections.network.config.BaseUrls;

// DesignSample
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        ViewCompat.setTransitionName(imageView, MainActivity.TRANSITION_PIC);// <- animation
        if (null != getIntent().getSerializableExtra("DetailData")) {
            DetailFragment.DetailData myObject = (DetailFragment.DetailData) getIntent().getSerializableExtra("DetailData");
            Glide.with(DetailActivity.this)
                    .load(BaseUrls.MOVIE_IMAGE_URL + myObject.getImage())
                    .into(imageView);
        }
    }
}
