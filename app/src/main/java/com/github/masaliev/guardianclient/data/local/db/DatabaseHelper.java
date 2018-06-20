package com.github.masaliev.guardianclient.data.local.db;

import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;

import java.util.List;

public interface DatabaseHelper {
    void upgradeSections(List<AppSection> sections);
}
