package com.github.masaliev.guardianclient.data.model.news_element;

public class NewsElementVideo implements NewsElement {

    private String mUrl;

    private String mHtml;

    private VideoSource mVideoSource;

    public NewsElementVideo(String url, String html, VideoSource videoSource) {
        mUrl = url;
        mHtml = html;
        mVideoSource = videoSource;
    }

    @Override
    public NewsElementType getType() {
        return NewsElementType.VIDEO;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getHtml() {
        return mHtml;
    }

    public VideoSource getVideoSource() {
        return mVideoSource;
    }

    public enum VideoSource{
        YOUTUBE, GUARDIAN;

        public static VideoSource parse(String source){
            switch (source){
                case "YouTube": return YOUTUBE;
                case "Guardian": return GUARDIAN;
            }
            return null;
        }
    }
}
