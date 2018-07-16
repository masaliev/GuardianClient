package com.github.masaliev.guardianclient.data.model.news_element;

public class NewsElementText implements NewsElement{

    private String mHtml;

    public NewsElementText(String html){
        this.mHtml = html;
    }

    @Override
    public NewsElementType getType() {
        return NewsElementType.TEXT;
    }

    public String getHtml(){
        return mHtml;
    }
}
