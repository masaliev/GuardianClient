package com.github.masaliev.guardianclient.data.remote.repository;

import android.support.annotation.Nullable;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.model.PaginationResult;
import com.github.masaliev.guardianclient.data.model.Section;

import java.util.List;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<PaginationResult<? extends News>> getNews(@Nullable Section section, int page);

}
