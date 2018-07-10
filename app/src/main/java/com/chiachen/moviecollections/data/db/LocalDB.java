package com.chiachen.moviecollections.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.chiachen.moviecollections.models.Movie;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {
    public abstract MovieDao movieDao();
}
