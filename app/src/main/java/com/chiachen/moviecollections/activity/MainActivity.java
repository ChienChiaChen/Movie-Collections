package com.chiachen.moviecollections.activity;

import android.os.Bundle;

import com.chiachen.moviecollections.R;
import com.chiachen.moviecollections.base.MVPActivity;
import com.chiachen.moviecollections.di.component.DaggerMainComponent;
import com.chiachen.moviecollections.di.module.MainModule;
import com.chiachen.moviecollections.global.BaseApplication;
import com.chiachen.moviecollections.presenter.MainPresenter;
import com.chiachen.moviecollections.view.MainView;

import javax.inject.Inject;

public class MainActivity extends MVPActivity implements MainView {

    @Inject
    public MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .netComponent(BaseApplication.get(this).getNetComponent())
                .build().inject(this);

    }

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }
}
