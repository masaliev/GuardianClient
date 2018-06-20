package com.github.masaliev.guardianclient.data.local.db;

import com.github.masaliev.guardianclient.data.local.db.dao.AppSectionDao;
import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppDatabaseHelperTest {

    private AppDatabase mAppDatabase;
    private AppSectionDao mSectionDao;

    private DatabaseHelper mDatabaseHelper;

    @Before
    public void setUp() {
        mAppDatabase = mock(AppDatabase.class);
        mSectionDao = mock(AppSectionDao.class);
        when(mAppDatabase.getAppSectionDao())
                .thenReturn(mSectionDao);
        mDatabaseHelper = new AppDatabaseHelper(mAppDatabase);
    }

    @Test
    public void upgradeSections() {
        //Given
        List<AppSection> sections = new ArrayList<>();

        //When
        mDatabaseHelper.upgradeSections(sections);

        //Then
        verify(mAppDatabase).getAppSectionDao();
        verify(mSectionDao).upgradeSections(sections);
    }
}