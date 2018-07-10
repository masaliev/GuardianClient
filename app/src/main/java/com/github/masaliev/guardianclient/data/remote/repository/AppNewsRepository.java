package com.github.masaliev.guardianclient.data.remote.repository;

import android.support.annotation.Nullable;

import com.github.masaliev.guardianclient.data.model.AppNews;
import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.model.PaginationResult;
import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.data.remote.api.NewsApi;

import io.reactivex.Observable;

public class AppNewsRepository implements NewsRepository{

    private final NewsApi mNewsApi;


    public AppNewsRepository(NewsApi newsApi) {
        mNewsApi = newsApi;
    }

    @Override
    public Observable<PaginationResult<? extends News>> getNews(@Nullable Section section, int page) {
        return mNewsApi.getNews(section != null ? section.getId() : null, page)
                .flatMap(apiResult -> {
                    PaginationResult<AppNews> paginationResult = new PaginationResult<>();
                    paginationResult.currentPage = apiResult.response.currentPage;
                    paginationResult.totalPages = apiResult.response.totalPages;
                    paginationResult.results = apiResult.response.results;
                    return Observable.just(paginationResult);
                });
    }
}
