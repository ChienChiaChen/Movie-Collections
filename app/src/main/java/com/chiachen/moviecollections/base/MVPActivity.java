package com.chiachen.moviecollections.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


public abstract class MVPActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;
    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        initUI();
    }

    protected abstract void initUI();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
