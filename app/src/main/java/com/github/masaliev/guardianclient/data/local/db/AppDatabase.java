package com.github.masaliev.guardianclient.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.github.masaliev.guardianclient.data.local.db.dao.AppSectionDao;
import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;

@Database(entities = {AppSection.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppSectionDao getAppSectionDao();
}
