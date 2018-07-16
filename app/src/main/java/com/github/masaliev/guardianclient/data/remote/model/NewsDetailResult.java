package com.github.masaliev.guardianclient.data.remote.model;

import com.github.masaliev.guardianclient.data.model.AppNews;
import com.google.gson.annotations.Expose;

public class NewsDetailResult {

    @Expose
    public NewsDetailResultResponse response;


    public class NewsDetailResultResponse {
        @Expose
        public AppNews content;
    }
}
