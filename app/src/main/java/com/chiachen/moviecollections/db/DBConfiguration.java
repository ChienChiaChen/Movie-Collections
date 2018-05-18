package com.chiachen.moviecollections.db;

/**
 * Created by jianjiacheng on 2018/05/17.
 */

public interface DBConfiguration {
    String DB_NAME = "MovieCollections";
    String MOVIES_TABLE_NAME = "Movies";
    String MOVIES_ID = "id";
    String MOVIES_TITLE = "title" ;
    String MOVIES_RELEASE_DATE= "release_date" ;
    String MOVIES_IMAGE = "image";
    String MOVIES_OVERVIEW = "overview";

    String MOVIES_SELECT_ALL = "SELECT * FROM " + DBConfiguration.MOVIES_TABLE_NAME;
}
