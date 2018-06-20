package com.github.masaliev.guardianclient.ui.base;

import com.github.masaliev.guardianclient.utils.TestHelper;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.disposables.CompositeDisposable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BaseViewModelTest {
    @Mock
    private BaseNavigator mBaseNavigator;

    @Mock
    private SchedulerProvider mSchedulerProvider;

    private BaseViewModel<BaseNavigator> mBaseViewModel;

    @Before
    public void setUp() {
        mBaseViewModel = new BaseViewModel<>(mSchedulerProvider);
    }

    @Test
    public void setNavigator() {
        mBaseViewModel.setNavigator(mBaseNavigator);

        assertNotNull(mBaseViewModel.getNavigator());
    }

    @Test
    public void getNavigator() throws Exception {
        TestHelper.setPrivateFieldValue(mBaseViewModel, "mNavigator", mBaseNavigator);
        assertEquals(mBaseNavigator, mBaseViewModel.getNavigator());
    }

    @Test
    public void getCompositeDisposable() throws Exception{
        CompositeDisposable compositeDisposable = mock(CompositeDisposable.class);
        TestHelper.setPrivateFieldValue(mBaseViewModel, "mCompositeDisposable", compositeDisposable);

        assertEquals(compositeDisposable, mBaseViewModel.getCompositeDisposable());
    }

    @Test
    public void getSchedulerProvider() {
        assertEquals(mSchedulerProvider, mBaseViewModel.getSchedulerProvider());
    }

    @Test
    public void onViewCreated() throws Exception{
        TestHelper.setPrivateFieldValue(mBaseViewModel, "mCompositeDisposable", null);
        mBaseViewModel.onViewCreated();
        assertNotNull(mBaseViewModel.getCompositeDisposable());
    }

    @Test
    public void onDestroyView() throws Exception{
        CompositeDisposable compositeDisposable = mock(CompositeDisposable.class);
        TestHelper.setPrivateFieldValue(mBaseViewModel, "mCompositeDisposable", compositeDisposable);

        mBaseViewModel.onDestroyView();
        verify(compositeDisposable).dispose();
    }
}