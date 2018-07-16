package com.github.masaliev.guardianclient.data.model.news_element;

public class NewsElementRichLink implements NewsElement{

    private String mUrl;

    private String mText;

    private String mLinkPrefix;

    public NewsElementRichLink(String url, String text, String linkPrefix) {
        mUrl = url;
        mText = text;
        mLinkPrefix = linkPrefix;
    }

    @Override
    public NewsElementType getType() {
        return NewsElementType.RICH_LINK;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getText() {
        if(mLinkPrefix != null){
            return mLinkPrefix + " " + mText;
        }else {
            return mText;
        }
    }
}
