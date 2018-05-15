package com.chiachen.moviecollections.view;

import com.chiachen.moviecollections.base.BaseView;
import com.chiachen.moviecollections.models.MoviesResponse;

import java.util.Map;

/**
 * Created by jianjiacheng on 14/05/2018.
 */

public interface MainView extends BaseView {
    void notifyAdapter(Map<Integer, MoviesResponse> model);
}
