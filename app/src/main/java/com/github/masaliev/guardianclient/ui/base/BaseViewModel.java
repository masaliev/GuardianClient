package com.github.masaliev.guardianclient.ui.base;

import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N> {

    private N mNavigator;

    private final SchedulerProvider mSchedulerProvider;

    public BaseViewModel(SchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    private CompositeDisposable mCompositeDisposable;


    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public void onViewCreated(){
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public void onDestroyView(){
        mCompositeDisposable.dispose();
    }
}
