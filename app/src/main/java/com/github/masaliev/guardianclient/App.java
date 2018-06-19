package com.github.masaliev.guardianclient;

import android.app.Application;
import android.content.Context;

import com.github.masaliev.guardianclient.di.AppComponent;
import com.github.masaliev.guardianclient.di.DaggerAppComponent;
import com.github.masaliev.guardianclient.di.module.AppModule;

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
                .build();
    }
}
