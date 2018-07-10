package com.github.masaliev.guardianclient.ui.main;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.model.PaginationResult;
import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.utils.TestHelper;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;
import com.github.masaliev.guardianclient.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainViewModelTest {

    private SchedulerProvider mSchedulerProvider;
    private TestScheduler mTestScheduler;
    private NewsRepository mNewsRepository;

    private MainNavigator mNavigator;
    private MainViewModel mViewModel;

    @Before
    public void setUp() {
        mTestScheduler = new TestScheduler();
        mSchedulerProvider = spy(new TestSchedulerProvider(mTestScheduler));
        mNewsRepository = mock(NewsRepository.class);

        mNavigator = mock(MainNavigator.class);
        mViewModel = new MainViewModel(mSchedulerProvider, mNewsRepository);
        mViewModel.onViewCreated();
        mViewModel.setNavigator(mNavigator);
    }

    @Test
    public void getNews_okResult_correctCalls() throws Exception{
        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        PaginationResult<News> paginationResult = new PaginationResult<>();
        paginationResult.currentPage = 2;
        paginationResult.totalPages = 3;
        paginationResult.results = newsList;
        when(mNewsRepository.getNews(any(), anyInt()))
                .thenReturn(Observable.just(paginationResult));
        TestHelper.setPrivateFieldValue(mViewModel, "nextPage", 2);

        //When
        mViewModel.getNews();
        mTestScheduler.triggerActions();

        //Then
        verify(mNavigator).showProgress();
        verify(mNewsRepository).getNews(any(),eq(2));
        assertEquals(3, TestHelper.getPrivateFieldValue(mViewModel, "nextPage"));
        assertEquals(true, TestHelper.getPrivateFieldValue(mViewModel, "hasNext"));
        assertEquals(false, TestHelper.getPrivateFieldValue(mViewModel, "isLoading"));
        verify(mNavigator).populateNewsList(newsList, false);
        verify(mNavigator).hideProgress();
        verify(mNavigator, never()).handleError(any());
        verify(mSchedulerProvider).io();
        verify(mSchedulerProvider).ui();
    }

    @Test
    public void getNews_onError_correctCalls() throws Exception{
        //Given
        Throwable throwable = new IOException();
        when(mNewsRepository.getNews(any(), anyInt()))
                .thenReturn(Observable.error(throwable));
        TestHelper.setPrivateFieldValue(mViewModel, "nextPage", 2);

        //When
        mViewModel.getNews();
        mTestScheduler.triggerActions();

        //Then
        verify(mNavigator).showProgress();
        verify(mNewsRepository).getNews(null, 2);
        assertEquals(2, TestHelper.getPrivateFieldValue(mViewModel, "nextPage"));
        assertEquals(false, TestHelper.getPrivateFieldValue(mViewModel, "isLoading"));
        verify(mNavigator, never()).populateNewsList(anyList(), anyBoolean());
        verify(mNavigator).hideProgress();
        verify(mNavigator).handleError(throwable);
        verify(mSchedulerProvider).io();
        verify(mSchedulerProvider).ui();
    }

    @Test
    public void getNews_isLoading_doesNotCallsRepositoryMethod() throws Exception{
        //Given
        TestHelper.setPrivateFieldValue(mViewModel, "isLoading", true);

        //When
        mViewModel.getNews();

        //Then
        verify(mNewsRepository, never()).getNews(any(), anyInt());
        verify(mNavigator, never()).showProgress();
    }

    @Test
    public void getNews_hasNotNext_doesNotCallsRepositoryMethod() throws Exception{
        //Given
        TestHelper.setPrivateFieldValue(mViewModel, "hasNext", false);

        //When
        mViewModel.getNews();

        //Then
        verify(mNewsRepository, never()).getNews(any(), anyInt());
        verify(mNavigator, never()).showProgress();
    }

    @Test
    public void getNews_lastPageLoaded_hasNextFalse() throws Exception{
        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        PaginationResult<News> paginationResult = new PaginationResult<>();
        paginationResult.currentPage = 3;
        paginationResult.totalPages = 3;
        paginationResult.results = newsList;
        when(mNewsRepository.getNews(any(), anyInt()))
                .thenReturn(Observable.just(paginationResult));
        TestHelper.setPrivateFieldValue(mViewModel, "nextPage", 3);

        //When
        mViewModel.getNews();
        mTestScheduler.triggerActions();

        //Then
        verify(mNewsRepository).getNews(null,3);
        assertEquals(false, TestHelper.getPrivateFieldValue(mViewModel, "hasNext"));
    }

    @Test
    public void getNews_firstPageLoadede_resetItems(){
        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        PaginationResult<News> paginationResult = new PaginationResult<>();
        paginationResult.currentPage = 1;
        paginationResult.totalPages = 3;
        paginationResult.results = newsList;
        when(mNewsRepository.getNews(any(), anyInt()))
                .thenReturn(Observable.just(paginationResult));

        //When
        mViewModel.getNews();
        mTestScheduler.triggerActions();

        //Then
        verify(mNavigator).populateNewsList(newsList, true);
    }

    @Test
    public void resetPagination() throws Exception{
        //Given
        TestHelper.setPrivateFieldValue(mViewModel, "nextPage", 5);
        TestHelper.setPrivateFieldValue(mViewModel, "hasNext", false);

        //When
        mViewModel.resetPagination();

        //Then
        assertEquals(1, TestHelper.getPrivateFieldValue(mViewModel, "nextPage"));
        assertEquals(true, TestHelper.getPrivateFieldValue(mViewModel, "hasNext"));
    }
}