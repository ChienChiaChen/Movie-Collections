package com.chiachen.moviecollections.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chiachen.moviecollections.R;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    public Activity mActivity;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mActivity = this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mActivity = this;
    }

    public Toolbar initToolBarAsHome(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    private ProgressDialog progressDialog;

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(getString(R.string.progress_loading));
        progressDialog.show();
    }

    public void showProgressDialog(CharSequence msg) {
        dismissProgressDialog();
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showToastInShortTime(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastInLongTime(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void NoNetworkException() {
        showToastInShortTime("NoNetworkException");
    }

    @Override
    public void onUnknownError(String errorMessage) {
        showToastInShortTime(errorMessage);
    }

    @Override
    public void onTimeout() {
        showToastInShortTime("onTimeout");
    }

    @Override
    public void onNetworkError() {
        showToastInShortTime("onNetworkError");
    }
}
