package com.github.masaliev.guardianclient.data.remote.repository;

import com.github.masaliev.guardianclient.data.model.News;

import java.util.List;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<List<? extends News>> getNews();

}
