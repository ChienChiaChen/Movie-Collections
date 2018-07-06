package com.chiachen.moviecollections.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.adapter.MainAdapter;
import com.chiachen.moviecollections.adapter.VerticalAdapter;
import com.chiachen.moviecollections.adapter.ViewOnClickListener;
import com.chiachen.moviecollections.base.MVPActivity;
import com.chiachen.moviecollections.fragment.DetailFragment;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.presenter.MainPresenter;
import com.chiachen.moviecollections.utils.CollectionUtils;
import com.chiachen.moviecollections.view.MainView;

import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends MVPActivity implements MainView {
    public static final String TRANSITION_PIC = "transitionPic";
    public static final int INIT_PAGE_NUM =1;
    private final int VISIBLE_OFFSET = 1;
    private VerticalAdapter mVerticalAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem, totalItemCount;
    private int mPageNumber = INIT_PAGE_NUM;

    private ViewOnClickListener mViewOnClickListener = new ViewOnClickListener() {
        @Override
        public void onClickedView(RecyclerView.ViewHolder holder, View transitionView, DetailFragment.DetailData detailData) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, DetailActivity.class);
            intent.putExtra("DetailData", detailData);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, transitionView, MainActivity.TRANSITION_PIC);
            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (null == mMainPresenter) return;
            mMainPresenter.loadMovie(mPageNumber);
        }
    };

    @Inject
    public MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mMainPresenter.loadMovie(mPageNumber);
    }

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);
        initToobar();
        mVerticalAdapter = new VerticalAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(mVerticalAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalItemCount = mLinearLayoutManager.getItemCount();
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                if (!mRefreshLayout.isRefreshing() && totalItemCount <= (lastVisibleItem + VISIBLE_OFFSET)) {
                    mPageNumber++;
                    mRefreshListener.onRefresh();
                }
            }
        });
    }

    private void initToobar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void notifyAdapter(Map<Integer, MoviesResponse> model) {
        if (CollectionUtils.isNullOrEmpty(model)) return;
        for (Map.Entry<Integer, MoviesResponse> entry : model.entrySet()) {
            if (CollectionUtils.isNullOrEmpty(entry.getValue().results)){
                // show
                showToastInShortTime("Network is unavailable");
                return;
            }
        }

        mVerticalAdapter.setItemOnClickListener(mViewOnClickListener);
        mVerticalAdapter.setData(model.get(MainAdapter.VERTICAL));
    }

    @Override
    public void onBackPressed() {
        if (super.fragmentsBackKeyIntercept()){
            findViewById(R.id.recycler_View).setVisibility(View.VISIBLE);
            findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void dismissRefreshing() {
        if (null == mRefreshLayout) return;
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRefreshing() {
        if (null != mRefreshLayout) {
            mRefreshLayout.setRefreshing(false);
            mRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.action_search == id) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
