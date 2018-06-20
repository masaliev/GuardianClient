package com.github.masaliev.guardianclient.data.local.db.dao;

import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class AppSectionDaoTest {

    private AppSectionDao mSectionDao;

    @Before
    public void setUp() {
        mSectionDao = spy(AppSectionDao.class);
    }

    @Test
    public void upgradeSections() {
        //Given
        List<AppSection> sections = new ArrayList<>();

        //When
        mSectionDao.upgradeSections(sections);

        //Then
        verify(mSectionDao).insertAll(sections);
        verify(mSectionDao).deleteAll();
    }
}