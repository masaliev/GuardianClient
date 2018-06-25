package com.github.masaliev.guardianclient.data.remote.repository;

import com.github.masaliev.guardianclient.data.model.Section;

import java.util.List;

import io.reactivex.Observable;

public interface SectionRepository {

    Observable<List<Section>> getSections();

}
