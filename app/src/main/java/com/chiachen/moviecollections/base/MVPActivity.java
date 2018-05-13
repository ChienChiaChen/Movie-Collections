package com.chiachen.moviecollections.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


public abstract class MVPActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    protected abstract void initUI();
}
