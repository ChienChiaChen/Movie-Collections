package com.chiachen.moviecollections.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.adapter.MainAdapter;
import com.chiachen.moviecollections.base.MVPActivity;
import com.chiachen.moviecollections.di.component.DaggerMainComponent;
import com.chiachen.moviecollections.di.module.MainModule;
import com.chiachen.moviecollections.global.BaseApplication;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.presenter.MainPresenter;
import com.chiachen.moviecollections.utils.CollectionUtils;
import com.chiachen.moviecollections.view.MainView;

import java.util.Map;

import javax.inject.Inject;

public class MainActivity extends MVPActivity implements MainView {

    private MainAdapter mMainAdapter;

    @Inject
    public MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .netComponent(BaseApplication.get(this).getNetComponent())
                .build().inject(this);

        mMainPresenter.loadMovie();
    }

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);
        mMainAdapter = new MainAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMainAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void notifyAdapter(Map<Integer, MoviesResponse> model) {
        if (CollectionUtils.isNullOrEmpty(model)) return;

        mMainAdapter.setPopularItem(model.get(MainAdapter.VERTICAL));
        mMainAdapter.setUpcomingItem(model.get(MainAdapter.HORIZONTAL));
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

}
