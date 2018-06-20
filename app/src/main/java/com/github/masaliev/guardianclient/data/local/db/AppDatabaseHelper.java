package com.github.masaliev.guardianclient.data.local.db;

import com.github.masaliev.guardianclient.data.local.db.dao.AppSectionDao;
import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;

import java.util.List;

import javax.inject.Inject;

public class AppDatabaseHelper implements DatabaseHelper{

    private final AppSectionDao mSectionDao;

    @Inject
    public AppDatabaseHelper(AppDatabase appDatabase) {
        mSectionDao = appDatabase.getAppSectionDao();
    }

    @Override
    public void upgradeSections(List<AppSection> sections) {
        mSectionDao.upgradeSections(sections);
    }
}
