package com.chiachen.moviecollections.base;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showToastInShortTime(String msg);
    void showToastInLongTime(String msg);
}
