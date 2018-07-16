package com.github.masaliev.guardianclient.data.model;

import com.github.masaliev.guardianclient.data.model.news_element.NewsElement;

import java.util.Date;
import java.util.List;

public interface News {
    String getId();
    String getImageUrl();
    String getTitle();
    String getShortText();
    Date getDate();
    String getAuthor();

    List<? extends NewsElement> getElements();

}
