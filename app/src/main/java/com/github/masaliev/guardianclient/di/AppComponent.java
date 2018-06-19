package com.github.masaliev.guardianclient.di;

import com.github.masaliev.guardianclient.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {
}
