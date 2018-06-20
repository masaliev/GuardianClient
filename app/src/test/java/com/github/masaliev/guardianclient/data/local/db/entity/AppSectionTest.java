package com.github.masaliev.guardianclient.data.local.db.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppSectionTest {

    private AppSection mSection;

    @Before
    public void setUp() {
        mSection = new AppSection();
    }

    @Test
    public void getId() {
        //Given
        mSection.id = "football";

        //When
        String actualId = mSection.getId();

        //Then
        assertEquals("football", actualId);
    }

    @Test
    public void getTitle() {
        //Given
        mSection.title = "Football";

        //When
        String actualTitle = mSection.getTitle();

        //Then
        assertEquals("Football", actualTitle);
    }
}