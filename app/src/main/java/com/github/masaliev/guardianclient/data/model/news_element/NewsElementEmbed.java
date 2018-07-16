package com.github.masaliev.guardianclient.data.model.news_element;

public class NewsElementEmbed implements NewsElement {

    private String mHtml;

    public NewsElementEmbed(String html) {
        mHtml = html;
    }

    @Override
    public NewsElementType getType() {
        return NewsElementType.EMBED;
    }

    public String getHtml() {
        return mHtml;
    }
}
