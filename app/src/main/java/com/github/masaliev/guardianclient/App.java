package com.github.masaliev.guardianclient;

import android.app.Application;
import android.content.Context;

import com.github.masaliev.guardianclient.di.AppComponent;
import com.github.masaliev.guardianclient.di.DaggerAppComponent;
import com.github.masaliev.guardianclient.di.module.AppModule;
import com.github.masaliev.guardianclient.di.module.NetworkModule;

public class App extends Application{

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = prepareAppComponent();
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        return mComponent;
    }

    protected AppComponent prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://content.guardianapis.com/",
                        "69bd329c-7c48-4b63-b562-83725159bffc"))
                .build();
    }
}
