package com.github.masaliev.guardianclient.di;

import com.github.masaliev.guardianclient.di.module.ApiModule;
import com.github.masaliev.guardianclient.di.module.AppModule;
import com.github.masaliev.guardianclient.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        ApiModule.class,
})
public interface AppComponent {
}
