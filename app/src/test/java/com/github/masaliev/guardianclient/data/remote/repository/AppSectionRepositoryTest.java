package com.github.masaliev.guardianclient.data.remote.repository;

import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;
import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.data.remote.api.SectionApi;
import com.github.masaliev.guardianclient.data.remote.model.ApiResponse;
import com.github.masaliev.guardianclient.data.remote.model.ApiResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppSectionRepositoryTest {

    @Mock
    SectionApi mSectionApi;

    private SectionRepository mRepository;

    @Before
    public void setUp(){
        mRepository = new AppSectionRepository(mSectionApi);
    }

    @Test
    public void getSections_okResult_noErrors() {
        //Given
        ApiResult<AppSection> apiResult = new ApiResult<>();
        ApiResponse<AppSection> apiResponse = new ApiResponse<>();

        final List<AppSection> sectionList = new ArrayList<>();
        sectionList.add(mock(AppSection.class));
        sectionList.add(mock(AppSection.class));
        sectionList.add(mock(AppSection.class));
        sectionList.add(mock(AppSection.class));
        sectionList.add(mock(AppSection.class));

        apiResponse.currentPage = 1;
        apiResponse.totalPages = 1;
        apiResponse.results = sectionList;
        apiResult.response = apiResponse;
        when(mSectionApi.getSections())
                .thenReturn(Observable.just(apiResult));

        //When
        TestObserver<List<? extends Section>> testObserver = mRepository.getSections().test();
        testObserver.awaitTerminalEvent();

        //Then
        verify(mSectionApi).getSections();
        testObserver.assertNoErrors()
                .assertValue(sections -> sections.size() == sectionList.size());
    }

    @Test
    public void getSections_onErrorResponce_hasError(){
        //Given
        IOException error = new IOException();
        when(mSectionApi.getSections())
                .thenReturn(Observable.error(error));

        //When
        TestObserver<List<? extends Section>> testObserver = mRepository.getSections().test();
        testObserver.awaitTerminalEvent();

        //Then
        verify(mSectionApi).getSections();
        testObserver.assertError(error);
    }
}