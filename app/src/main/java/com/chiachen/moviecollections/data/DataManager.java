package com.chiachen.moviecollections.data;

import com.chiachen.moviecollections.data.db.DbHelper;
import com.chiachen.moviecollections.data.network.ApiHelper;
import com.chiachen.moviecollections.data.prefs.PrefHelper;

/**
 * Created by jianjiacheng on 2018/7/10.
 */

public interface DataManager extends ApiHelper, PrefHelper, DbHelper {
}
