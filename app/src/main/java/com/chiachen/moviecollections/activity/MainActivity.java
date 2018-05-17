package com.chiachen.moviecollections.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;

import com.chiachen.moviecollections.fragment.DetailFragment;
import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.adapter.MainAdapter;
import com.chiachen.moviecollections.adapter.ViewOnClickListener;
import com.chiachen.moviecollections.base.MVPActivity;
import com.chiachen.moviecollections.di.component.DaggerMainComponent;
import com.chiachen.moviecollections.di.module.MainModule;
import com.chiachen.moviecollections.global.BaseApplication;
import com.chiachen.moviecollections.models.MoviesResponse;
import com.chiachen.moviecollections.presenter.MainPresenter;
import com.chiachen.moviecollections.utils.CollectionUtils;
import com.chiachen.moviecollections.utils.DetailTransition;
import com.chiachen.moviecollections.view.MainView;

import java.util.Map;

import javax.inject.Inject;

public class MainActivity extends MVPActivity implements MainView {

    private MainAdapter mMainAdapter;
    private ViewOnClickListener mViewOnClickListener = new ViewOnClickListener() {
        @Override
        public void onClickedView(RecyclerView.ViewHolder holder, View transitionView, DetailFragment.DetailData detailData) {
            DetailFragment detailFragment = DetailFragment.newInstance(detailData);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                detailFragment.setSharedElementEnterTransition(new DetailTransition());
                // setExitTransition(new Fade());
                detailFragment.setEnterTransition(new Fade());
                detailFragment.setSharedElementReturnTransition(new DetailTransition());
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement(transitionView, getResources().getString(R.string.image_transition))
                    .replace(R.id.fragment_container, detailFragment)
                    .commit();

            findViewById(R.id.recycler_View).setVisibility(View.INVISIBLE);
            findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        }
    };

    @Inject
    public MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .netComponent(BaseApplication.get(this).getNetComponent())
                .build()
                .inject(this);

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

        mMainAdapter.setItemOnClickListener(mViewOnClickListener);
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

    @Override
    public void onBackPressed() {
        if (super.fragmentsBackKeyIntercept()){
            findViewById(R.id.recycler_View).setVisibility(View.VISIBLE);
            findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);
            return;
        }
        super.onBackPressed();
    }
}
