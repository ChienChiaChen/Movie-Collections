package com.chiachen.moviecollections.presenter;

import com.chiachen.moviecollections.base.BasePresenter;
import com.chiachen.moviecollections.network.ApiService;
import com.chiachen.moviecollections.view.MainView;

import javax.inject.Inject;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    public MainPresenter(MainView mainView, ApiService apiService) {
        attachView(mainView);
    }
}
