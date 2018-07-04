package com.github.masaliev.guardianclient.ui.main;

import android.databinding.ObservableBoolean;

import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.ui.base.BaseViewModel;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final NewsRepository mNewsRepository;

    public ObservableBoolean isEmptyList = new ObservableBoolean(false);

    public MainViewModel(SchedulerProvider schedulerProvider, NewsRepository newsRepository) {
        super(schedulerProvider);
        mNewsRepository = newsRepository;
    }

    public void getNews(){
        getNavigator().showProgress();
        getCompositeDisposable().add(mNewsRepository.getNews()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(news -> {
                    getNavigator().hideProgress();
                    getNavigator().populateNewsList(news);
                }, throwable -> {
                    getNavigator().hideProgress();
                    getNavigator().handleError(throwable);
                }));
    }
}
