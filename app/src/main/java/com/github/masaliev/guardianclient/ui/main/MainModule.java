package com.github.masaliev.guardianclient.ui.main;

import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    MainViewModel provideMainViewModel(SchedulerProvider schedulerProvider,
                                       NewsRepository newsRepository){
        return new MainViewModel(schedulerProvider, newsRepository);
    }
}
