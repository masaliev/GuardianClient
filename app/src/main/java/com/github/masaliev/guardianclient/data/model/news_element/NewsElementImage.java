package com.github.masaliev.guardianclient.data.model.news_element;

public class NewsElementImage implements NewsElement {

    private String mCaption;

    private String mImageUrl;

    private String mCredit;

    private boolean mDisplayCredit;

    public NewsElementImage(String caption, String imageUrl, String credit, boolean displayCredit) {
        mCaption = caption;
        mImageUrl = imageUrl;
        mCredit = credit;
        mDisplayCredit = displayCredit;
    }

    @Override
    public NewsElementType getType() {
        return NewsElementType.IMAGE;
    }

    public String getCaption() {
        if (mDisplayCredit){
            return mCaption + " " + mCredit;
        }else {
            return mCaption;
        }
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
