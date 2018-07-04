package com.github.masaliev.guardianclient.ui.base;

public interface BaseNavigator {
    void showProgress();
    void hideProgress();
    void handleError(Throwable throwable);
}
