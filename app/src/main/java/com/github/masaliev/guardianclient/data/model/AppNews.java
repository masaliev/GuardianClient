package com.github.masaliev.guardianclient.data.model;

import com.github.masaliev.guardianclient.data.model.news_element.NewsBody;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Expose
    @SerializedName("blocks")
    public NewsBody elements;

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

    @Override
    public String getAuthor() {
        return fields != null ? fields.byline : null;
    }

    @Override
    public List<? extends NewsElement> getElements() {
        return elements;
    }
}
