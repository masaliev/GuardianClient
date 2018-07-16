package com.github.masaliev.guardianclient.ui.news_details;

import android.databinding.ObservableField;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.ui.base.BaseViewModel;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

public class NewsDetailsViewModel extends BaseViewModel<NewsDetailsNavigator> {

    private final NewsRepository mNewsRepository;

    private ObservableField<News> news = new ObservableField<>();

    public NewsDetailsViewModel(SchedulerProvider schedulerProvider, NewsRepository newsRepository) {
        super(schedulerProvider);
        mNewsRepository = newsRepository;
    }


    public void setNews(News news){
        this.news.set(news);
    }

    public ObservableField<News> getNews() {
        return news;
    }

    public void fetchNews(String id){
        getCompositeDisposable().add(mNewsRepository.getNewsById(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(news -> {
                    this.news.set(news);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

}
