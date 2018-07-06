package com.github.masaliev.guardianclient.ui.main;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.ui.base.BaseNavigator;

import java.util.List;

public interface MainNavigator extends BaseNavigator {
    void populateNewsList(List<? extends News> newsList, boolean reset);
}
