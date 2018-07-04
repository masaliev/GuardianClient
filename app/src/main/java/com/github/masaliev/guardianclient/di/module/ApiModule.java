package com.github.masaliev.guardianclient.di.module;

import com.github.masaliev.guardianclient.data.remote.api.NewsApi;
import com.github.masaliev.guardianclient.data.remote.api.SectionApi;
import com.github.masaliev.guardianclient.data.remote.repository.AppNewsRepository;
import com.github.masaliev.guardianclient.data.remote.repository.AppSectionRepository;
import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.data.remote.repository.SectionRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    public SectionApi provideSectionApi(Retrofit retrofit){
        return retrofit.create(SectionApi.class);
    }

    @Provides
    public SectionRepository provideSectionRepository(SectionApi sectionApi){
        return new AppSectionRepository(sectionApi);
    }

    @Provides
    public NewsApi provideNewsApi(Retrofit retrofit){
        return retrofit.create(NewsApi.class);
    }

    @Provides
    public NewsRepository provideNewsRepository(NewsApi newsApi){
        return new AppNewsRepository(newsApi);
    }

}
