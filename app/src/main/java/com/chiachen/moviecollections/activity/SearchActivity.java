package com.chiachen.moviecollections.activity;

import android.os.Bundle;

import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.base.MVPActivity;
import com.chiachen.moviecollections.view.SearchView;

public class SearchActivity extends MVPActivity implements SearchView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_search);
    }
}
