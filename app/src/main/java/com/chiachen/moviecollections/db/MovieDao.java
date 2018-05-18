package com.chiachen.moviecollections.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.chiachen.moviecollections.models.Movie;

import java.util.List;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Movie> items);

    @Query(DBConfiguration.MOVIES_SELECT_ALL)
    List<Movie> getAll();
}
