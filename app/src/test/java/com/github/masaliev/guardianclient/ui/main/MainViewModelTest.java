package com.github.masaliev.guardianclient.ui.main;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;
import com.github.masaliev.guardianclient.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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
    public void getNews_okResult_correctCalls() {
        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        when(mNewsRepository.getNews())
                .thenReturn(Observable.just(newsList));

        //When
        mViewModel.getNews();
        mTestScheduler.triggerActions();

        //Then
        verify(mNavigator).showProgress();
        verify(mNewsRepository).getNews();
        verify(mNavigator).populateNewsList(newsList);
        verify(mNavigator).hideProgress();
        verify(mNavigator, never()).handleError(any());
        verify(mSchedulerProvider).io();
        verify(mSchedulerProvider).ui();
    }

    @Test
    public void getNews_onError_correctCalls() {
        //Given
        Throwable throwable = new IOException();
        when(mNewsRepository.getNews())
                .thenReturn(Observable.error(throwable));

        //When
        mViewModel.getNews();
        mTestScheduler.triggerActions();

        //Then
        verify(mNavigator).showProgress();
        verify(mNewsRepository).getNews();
        verify(mNavigator, never()).populateNewsList(anyList());
        verify(mNavigator).hideProgress();
        verify(mNavigator).handleError(throwable);
        verify(mSchedulerProvider).io();
        verify(mSchedulerProvider).ui();
    }
}