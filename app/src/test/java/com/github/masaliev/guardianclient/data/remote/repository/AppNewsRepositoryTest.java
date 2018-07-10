package com.github.masaliev.guardianclient.data.remote.repository;

import com.github.masaliev.guardianclient.data.model.AppNews;
import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.model.PaginationResult;
import com.github.masaliev.guardianclient.data.remote.api.NewsApi;
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
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AppNewsRepositoryTest {

    @Mock
    private NewsApi mNewsApi;

    private NewsRepository mNewsRepository;

    @Before
    public void setUp() {
        mNewsRepository = new AppNewsRepository(mNewsApi);
    }

    @Test
    public void getNews_okResult_noErrors() {
        //Given
        ApiResult<AppNews> apiResult = new ApiResult<>();
        ApiResponse<AppNews> apiResponse = new ApiResponse<>();

        List<AppNews> newsList = new ArrayList<>();
        newsList.add(mock(AppNews.class));
        newsList.add(mock(AppNews.class));
        newsList.add(mock(AppNews.class));
        newsList.add(mock(AppNews.class));

        int page = 1;
        apiResponse.currentPage = 1;
        apiResponse.totalPages = 2;
        apiResponse.results = newsList;
        apiResult.response = apiResponse;
        when(mNewsApi.getNews(any(), anyInt()))
                .thenReturn(Observable.just(apiResult));

        //When
        TestObserver<PaginationResult<? extends News>> testObserver = mNewsRepository.getNews(null, page).test();
        testObserver.awaitTerminalEvent();

        //Then
        verify(mNewsApi).getNews(null, page);
        testObserver.assertNoErrors()
                .assertValue(paginationResult -> paginationResult.currentPage == 1
                        && paginationResult.totalPages == 2
                        && paginationResult.results.size() == newsList.size());
    }


    @Test
    public void getNews_onErrorResponse_hasError(){
        //Given
        int page = 2;
        IOException error = new IOException();
        when(mNewsApi.getNews(any(), anyInt()))
                .thenReturn(Observable.error(error));

        //When
        TestObserver<PaginationResult<? extends News>> testObserver = mNewsRepository.getNews(null, page).test();
        testObserver.awaitTerminalEvent();

        //Then
        verify(mNewsApi).getNews(null, page);
        testObserver.assertError(error);
    }
}