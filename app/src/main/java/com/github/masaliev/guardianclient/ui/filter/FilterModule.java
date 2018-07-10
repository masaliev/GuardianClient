package com.github.masaliev.guardianclient.ui.filter;

import com.github.masaliev.guardianclient.data.local.db.DatabaseHelper;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class FilterModule {

    @Provides
    public FilterViewModel provideFilterViewModel(SchedulerProvider schedulerProvider,
                                                  DatabaseHelper databaseHelper){
        return new FilterViewModel(schedulerProvider, databaseHelper);
    }
}
