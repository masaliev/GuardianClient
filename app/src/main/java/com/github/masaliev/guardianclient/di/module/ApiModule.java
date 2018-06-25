package com.github.masaliev.guardianclient.di.module;

import com.github.masaliev.guardianclient.data.remote.api.SectionApi;

import dagger.Module;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    public SectionApi provideSectionApi(Retrofit retrofit){
        return retrofit.create(SectionApi.class);
    }

}
