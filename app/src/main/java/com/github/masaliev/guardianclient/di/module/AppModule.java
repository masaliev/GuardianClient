package com.github.masaliev.guardianclient.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.github.masaliev.guardianclient.data.local.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "GuardianClient")
                .allowMainThreadQueries()
                .build();
    }
}
