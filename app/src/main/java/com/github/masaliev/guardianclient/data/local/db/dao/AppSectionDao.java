package com.github.masaliev.guardianclient.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;

import java.util.List;

@Dao
public abstract class AppSectionDao {

    @Insert
    abstract void insertAll(List<AppSection> sections);

    @Query("DELETE FROM AppSection")
    abstract void deleteAll();

    @Transaction
    public void upgradeSections(List<AppSection> newSections){
        deleteAll();
        insertAll(newSections);
    }

    @Query("SELECT * FROM AppSection ORDER BY title")
    public abstract List<AppSection> getAll();
}
