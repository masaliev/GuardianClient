package com.github.masaliev.guardianclient.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.github.masaliev.guardianclient.data.model.news_element.NewsBody;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElement;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementEmbed;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementImage;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementRichLink;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementText;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementType;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementVideo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BindingHelper {

    @BindingAdapter(value = {"imageUrl", "progressView"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, @IdRes int progressView) {
        Picasso picasso;
        try{
            picasso = Picasso.get();
        }catch (IllegalStateException e){
            picasso = new Picasso.Builder(imageView.getContext()).build();
        }
        RequestCreator requestCreator = picasso.load(imageUrl);


        if (progressView != 0) {
            try {
                final View progress = ((View) imageView.getParent()).findViewById(progressView);
                progress.setVisibility(View.VISIBLE);
                requestCreator.into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progress.setVisibility(View.GONE);
                    }
                });
                return;
            } catch (Exception ignored) {
            }
        }

        requestCreator.into(imageView);
    }

    @BindingAdapter(value = {"date", "dateFormat"}, requireAll = false)
    public static void formatDate(TextView textView, Date date, String dateFormat){
        if(date == null){
            textView.setText(null);
            return;
        }

        if(dateFormat == null || dateFormat.length() == 0){
            dateFormat = "dd-MM-yyyy HH:mm";
        }

        try {
            textView.setText(new SimpleDateFormat(dateFormat, Locale.getDefault()).format(date));
        }catch (Exception ignored){
            ignored.printStackTrace();
            textView.setText(null);
        }
    }

    @BindingAdapter("html")
    public static void setHtml(TextView textView, String html){
        if(html == null){
            textView.setText(null);
        }else {

            textView.setText(Html.fromHtml(html));
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("newsElements")
    public static void addNewsElements(LinearLayout linearLayout, List<? extends NewsElement> newsElements){
        if(newsElements != null){
            for(NewsElement element : newsElements){
                if (element.getType() == NewsElementType.TEXT){
                    TextView textView = new TextView(linearLayout.getContext());
                    setHtml(textView, ((NewsElementText) element).getHtml());
                    linearLayout.addView(textView);
                } else if (element.getType() == NewsElementType.IMAGE){
                    ImageView imageView = new ImageView(linearLayout.getContext());
                    NewsElementImage imageElement = (NewsElementImage) element;

                    loadImage(imageView, imageElement.getImageUrl(), 0);

                    TextView textView = new TextView(linearLayout.getContext());
                    setHtml(textView, imageElement.getCaption());

                    linearLayout.addView(imageView);
                    linearLayout.addView(textView);
                } else if(element.getType() == NewsElementType.RICH_LINK){
                    NewsElementRichLink richLink = (NewsElementRichLink) element;

                    TextView textView = new TextView(linearLayout.getContext());
                    setHtml(textView, richLink.getText());

                    textView.setOnClickListener(v -> linearLayout.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(richLink.getUrl()))));

                    linearLayout.addView(textView);
                } else if(element.getType() == NewsElementType.EMBED){
                    NewsElementEmbed embed = (NewsElementEmbed) element;

//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);

//                    layoutParams.setMargins(0, 10, 0, 10);

                    WebView webView = new WebView(linearLayout.getContext());
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            return false;
                        }
                    });

                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webView.loadData(embed.getHtml(), "text/html", "utf-8");
                    linearLayout.addView(webView);
//                    linearLayout.addView(webView, layoutParams);
                } else if (element.getType() == NewsElementType.VIDEO){
                    NewsElementVideo video = (NewsElementVideo) element;

                    if(video.getVideoSource() == NewsElementVideo.VideoSource.GUARDIAN){
                        VideoView videoView = new VideoView(linearLayout.getContext());
                        videoView.setVideoURI(Uri.parse(video.getUrl()));
                        MediaController mediaController = new MediaController(linearLayout.getContext());
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        linearLayout.addView(videoView);
                    }else if (video.getVideoSource() == NewsElementVideo.VideoSource.YOUTUBE){
                        WebView webView = new WebView(linearLayout.getContext());
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                return false;
                            }
                        });

                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        webView.loadData(video.getHtml(), "text/html", "utf-8");
                        linearLayout.addView(webView);
                    }
                }
            }
        }
    }
}
