package com.chiachen.moviecollections.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.chiachen.moviecollections.db.DBConfiguration;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

@Entity(tableName = DBConfiguration.MOVIES_TABLE_NAME, indices = {@Index(value = {DBConfiguration.MOVIES_TITLE}, unique = true)})
public class Movie {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConfiguration.MOVIES_ID)
    private int id;

    @ColumnInfo(name = DBConfiguration.MOVIES_TITLE)
    private String title;

    @ColumnInfo(name = DBConfiguration.MOVIES_RELEASE_DATE)
    private String releaseTitle;

    @ColumnInfo(name = DBConfiguration.MOVIES_IMAGE)
    private String image;

    @ColumnInfo(name = DBConfiguration.MOVIES_OVERVIEW)
    private String overview;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseTitle() {
        return releaseTitle;
    }

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
