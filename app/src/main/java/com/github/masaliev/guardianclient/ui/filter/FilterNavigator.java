package com.github.masaliev.guardianclient.ui.filter;

import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.ui.base.BaseNavigator;

import java.util.List;

public interface FilterNavigator extends BaseNavigator {
    void populateSectionList(List<? extends Section> sections);
}
