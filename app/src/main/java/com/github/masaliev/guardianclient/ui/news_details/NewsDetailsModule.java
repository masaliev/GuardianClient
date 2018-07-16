package com.github.masaliev.guardianclient.ui.news_details;

import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsDetailsModule {

    @Provides
    public NewsDetailsViewModel provideNewsDetailsViewModel(SchedulerProvider schedulerProvider,
                                                            NewsRepository newsRepository){
        return new NewsDetailsViewModel(schedulerProvider, newsRepository);
    }
}
