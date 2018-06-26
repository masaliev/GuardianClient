package com.github.masaliev.guardianclient.data.local.db;

import com.github.masaliev.guardianclient.data.model.Section;

import java.util.List;

public interface DatabaseHelper {
    void upgradeSections(List<? extends Section> sections);
}
