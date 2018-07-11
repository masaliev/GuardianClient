package com.github.masaliev.guardianclient.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.IdRes;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.text.SimpleDateFormat;
import java.util.Date;
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
}
