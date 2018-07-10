package com.github.masaliev.guardianclient.data.local.db;

import com.github.masaliev.guardianclient.data.local.db.dao.AppSectionDao;
import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;
import com.github.masaliev.guardianclient.data.model.Section;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppDatabaseHelper implements DatabaseHelper{

    private final AppSectionDao mSectionDao;

    @Inject
    public AppDatabaseHelper(AppDatabase appDatabase) {
        mSectionDao = appDatabase.getAppSectionDao();
    }

    @Override
    public void upgradeSections(List<? extends Section> sections) {

        mSectionDao.upgradeSections((List<AppSection>) sections);
    }

    @Override
    public Observable<List<? extends Section>> getSections() {
        return Observable.defer(() -> Observable.just(mSectionDao.getAll()));
    }
}
