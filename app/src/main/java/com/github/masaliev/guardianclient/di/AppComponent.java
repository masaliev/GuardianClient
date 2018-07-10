package com.github.masaliev.guardianclient.di;

import com.github.masaliev.guardianclient.di.module.ApiModule;
import com.github.masaliev.guardianclient.di.module.AppModule;
import com.github.masaliev.guardianclient.di.module.NetworkModule;
import com.github.masaliev.guardianclient.ui.filter.FilterActivity;
import com.github.masaliev.guardianclient.ui.filter.FilterModule;
import com.github.masaliev.guardianclient.ui.main.MainActivity;
import com.github.masaliev.guardianclient.ui.main.MainModule;
import com.github.masaliev.guardianclient.ui.splash.SplashActivity;
import com.github.masaliev.guardianclient.ui.splash.SplashModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        ApiModule.class,
        SplashModule.class,
        MainModule.class,
        FilterModule.class,
})
public interface AppComponent {
    void inject(SplashActivity activity);
    void inject(MainActivity activity);
    void inject(FilterActivity activity);
}
