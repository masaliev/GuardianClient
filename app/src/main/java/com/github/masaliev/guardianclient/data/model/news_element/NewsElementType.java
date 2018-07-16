package com.github.masaliev.guardianclient.data.model.news_element;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum NewsElementType implements Serializable{
    @SerializedName("text")
    TEXT,

    @SerializedName("rich-link")
    RICH_LINK,

    @SerializedName("image")
    IMAGE,

    @SerializedName("embed")
    EMBED,

    @SerializedName("video")
    VIDEO;

    public static NewsElementType parse(String string){
        switch (string){
            case "text": return TEXT;
            case "rich-link": return RICH_LINK;
            case "image": return IMAGE;
            case "embed": return EMBED;
            case "video": return VIDEO;
        }
        return null;
    }
}
