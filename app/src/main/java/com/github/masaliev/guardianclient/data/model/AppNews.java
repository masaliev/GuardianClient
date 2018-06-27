package com.github.masaliev.guardianclient.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class AppNews implements Serializable, News {

    @Expose
    public String id;

    @Expose
    public AppNewsFields fields;

    @Expose
    @SerializedName("webTitle")
    public String title;

    @Expose
    @SerializedName("webPublicationDate")
    public Date date;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getImageUrl() {
        return fields != null ? fields.thumbnail : null;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getShortText() {
        return fields != null ? fields.shortText : null;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
