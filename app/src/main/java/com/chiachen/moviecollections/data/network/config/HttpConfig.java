package com.chiachen.moviecollections.data.network.config;


public interface HttpConfig {
    String TAG_LOG_REQUEST = "TAG_LOG_REQUEST";
    String TAG_LOG_RESPONSE = "TAG_LOG_RESPONSE";
    String TAG_LOG_HEADER = "TAG_LOG_HEADER";
    boolean NEED_TO_RETRY = true;
    int DEFAULT_TIMEOUT = 15;
}
