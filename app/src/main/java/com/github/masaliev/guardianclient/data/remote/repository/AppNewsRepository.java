package com.github.masaliev.guardianclient.data.remote.repository;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.remote.api.NewsApi;

import java.util.List;

import io.reactivex.Observable;

public class AppNewsRepository implements NewsRepository{

    private final NewsApi mNewsApi;


    public AppNewsRepository(NewsApi newsApi) {
        mNewsApi = newsApi;
    }

    @Override
    public Observable<List<? extends News>> getNews() {
        return mNewsApi.getNews()
                .flatMap(apiResult -> Observable.just(apiResult.response.results));
    }
}
