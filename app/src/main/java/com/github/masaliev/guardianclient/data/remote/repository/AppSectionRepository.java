package com.github.masaliev.guardianclient.data.remote.repository;


import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.data.remote.api.SectionApi;

import java.util.List;

import io.reactivex.Observable;

public class AppSectionRepository implements SectionRepository{

    private final SectionApi mSectionApi;

    public AppSectionRepository(SectionApi sectionApi) {
        mSectionApi = sectionApi;
    }


    @Override
    public Observable<List<? extends Section>> getSections() {
        return mSectionApi.getSections().flatMap(apiResult -> Observable.just(apiResult.response.results));
    }
}
