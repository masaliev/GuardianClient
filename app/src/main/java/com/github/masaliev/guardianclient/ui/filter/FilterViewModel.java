package com.github.masaliev.guardianclient.ui.filter;

import com.github.masaliev.guardianclient.data.local.db.DatabaseHelper;
import com.github.masaliev.guardianclient.ui.base.BaseViewModel;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

public class FilterViewModel extends BaseViewModel<FilterNavigator> {

    private final DatabaseHelper mDatabaseHelper;

    public FilterViewModel(SchedulerProvider schedulerProvider, DatabaseHelper databaseHelper) {
        super(schedulerProvider);
        mDatabaseHelper = databaseHelper;
    }

    public void getSections(){
        getCompositeDisposable().add(mDatabaseHelper.getSections()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(sections -> getNavigator().populateSectionList(sections), throwable -> getNavigator().handleError(throwable)));
    }

}
