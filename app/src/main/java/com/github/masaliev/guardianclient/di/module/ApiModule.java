package com.github.masaliev.guardianclient.di.module;

import com.github.masaliev.guardianclient.data.remote.api.SectionApi;
import com.github.masaliev.guardianclient.data.remote.repository.AppSectionRepository;
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


}
