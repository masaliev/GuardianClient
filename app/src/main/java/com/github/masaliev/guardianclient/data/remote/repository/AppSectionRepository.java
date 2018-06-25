package com.github.masaliev.guardianclient.data.remote.repository;


import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.data.remote.api.SectionApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppSectionRepository implements SectionRepository{

    private final SectionApi mSectionApi;

    @Inject
    public AppSectionRepository(SectionApi sectionApi) {
        mSectionApi = sectionApi;
    }


    @Override
    public Observable<List<Section>> getSections() {
        return mSectionApi.getSections().flatMap(apiResult -> {
            List<Section> list = new ArrayList<>(apiResult.response.results);
            return Observable.just(list);
        });
    }
}
