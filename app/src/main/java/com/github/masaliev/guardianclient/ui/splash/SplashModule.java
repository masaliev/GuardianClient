package com.github.masaliev.guardianclient.ui.splash;

import com.github.masaliev.guardianclient.data.local.db.DatabaseHelper;
import com.github.masaliev.guardianclient.data.remote.repository.SectionRepository;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    SplashViewModel provideSplashViewModel(SchedulerProvider schedulerProvider,
                                           DatabaseHelper databaseHelper,
                                           SectionRepository sectionRepository){
        return new SplashViewModel(schedulerProvider, databaseHelper, sectionRepository);
    }

}
