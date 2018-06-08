package com.chiachen.moviecollections.base;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public interface BaseView {
    void showToastInShortTime(String msg);
    void showToastInLongTime(String msg);
    void showProgressDialog();
    void showProgressDialog(CharSequence msg);
    void dismissProgressDialog();
    void NoNetworkException();
    void onUnknownError(String errorMessage);
    void onTimeout();
    void onNetworkError();
}
