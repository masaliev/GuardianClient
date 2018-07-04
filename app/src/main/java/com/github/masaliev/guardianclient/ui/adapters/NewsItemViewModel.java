package com.github.masaliev.guardianclient.ui.adapters;

import android.databinding.ObservableField;

import com.github.masaliev.guardianclient.data.model.News;

import java.util.Date;

public class NewsItemViewModel {
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> shortText = new ObservableField<>();
    public ObservableField<Date> date = new ObservableField<>();

    public NewsItemViewModel(News news) {
        imageUrl.set(news.getImageUrl());
        title.set(news.getTitle());
        shortText.set(news.getShortText());
        date.set(news.getDate());
    }
}
