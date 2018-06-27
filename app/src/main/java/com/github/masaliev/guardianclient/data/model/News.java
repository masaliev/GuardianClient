package com.github.masaliev.guardianclient.data.model;

import java.util.Date;

public interface News {
    String getId();
    String getImageUrl();
    String getTitle();
    String getShortText();
    Date getDate();
}
