package com.github.masaliev.guardianclient.data.remote.repository;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.model.PaginationResult;

import java.util.List;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<PaginationResult<? extends News>> getNews(int page);

}
