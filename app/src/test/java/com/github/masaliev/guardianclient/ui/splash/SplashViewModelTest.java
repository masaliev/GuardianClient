package com.github.masaliev.guardianclient.ui.splash;

import com.github.masaliev.guardianclient.data.local.db.DatabaseHelper;
import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;
import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.data.remote.repository.SectionRepository;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;
import com.github.masaliev.guardianclient.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SplashViewModelTest {

    private SchedulerProvider mSchedulerProvider;
    private TestScheduler mTestScheduler;
    private DatabaseHelper mDatabaseHelper;
    private SectionRepository mSectionRepository;

    private SplashNavigator mNavigator;
    private SplashViewModel mViewModel;


    @Before
    public void setUp() {
        mTestScheduler = new TestScheduler();
        mSchedulerProvider = spy(new TestSchedulerProvider(mTestScheduler));

        mDatabaseHelper = mock(DatabaseHelper.class);
        mSectionRepository = mock(SectionRepository.class);
        mNavigator = mock(SplashNavigator.class);
        mViewModel = new SplashViewModel(mSchedulerProvider, mDatabaseHelper, mSectionRepository);
        mViewModel.onViewCreated();
        mViewModel.setNavigator(mNavigator);
    }

    @Test
    public void upgradeSections_okResult_correctCalls() {
        //Given
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(mock(AppSection.class));
        sectionList.add(mock(AppSection.class));
        sectionList.add(mock(AppSection.class));
        when(mSectionRepository.getSections())
                .thenReturn(Observable.just(sectionList));

        //When
        mViewModel.upgradeSections();
        mTestScheduler.triggerActions();

        //Then
        verify(mSectionRepository).getSections();
        verify(mDatabaseHelper).upgradeSections(sectionList);
        verify(mNavigator).goToMainPage();
        verify(mSchedulerProvider).io();
        verify(mSchedulerProvider).ui();
    }

    @Test
    public void upgradeSections_onError_correctCalls() {
        //Given
        when(mSectionRepository.getSections())
                .thenReturn(Observable.error(IOException::new));

        //When
        mViewModel.upgradeSections();
        mTestScheduler.triggerActions();

        //Then
        verify(mSectionRepository).getSections();
        verify(mDatabaseHelper, never()).upgradeSections(anyList());
        verify(mNavigator).goToMainPage();
        verify(mSchedulerProvider).io();
        verify(mSchedulerProvider).ui();
    }
}