package com.github.masaliev.guardianclient.di.module;

import com.github.masaliev.guardianclient.BuildConfig;
import com.github.masaliev.guardianclient.data.remote.helper.ApiKeyInterceptor;
import com.github.masaliev.guardianclient.data.remote.helper.GsonUTCDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String mApiUrl;
    private final String mApiKey;


    public NetworkModule(String apiUrl, String apiKey) {
        mApiUrl = apiUrl;
        mApiKey = apiKey;
    }

    @Provides
    ApiKeyInterceptor provideApiKeyInterceptor(){
        return new ApiKeyInterceptor(mApiKey);
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                     ApiKeyInterceptor apiKeyInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();

    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson){
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mApiUrl)
                .build();

    }
}
