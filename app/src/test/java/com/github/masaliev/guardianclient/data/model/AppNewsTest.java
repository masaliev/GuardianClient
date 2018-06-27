package com.github.masaliev.guardianclient.data.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class AppNewsTest {

    private AppNews mNews;

    @Before
    public void setUp() {
        mNews = new AppNews();
    }

    @Test
    public void getId() {
        //Given
        mNews.id = "media/2018/jun/13/the-rev-colin-morris-obituary-letter";

        //When
        String actualId = mNews.getId();

        //Then
        assertEquals("media/2018/jun/13/the-rev-colin-morris-obituary-letter", actualId);
    }

    @Test
    public void getImageUrl() {
        //Given
        mNews.fields = new AppNewsFields();
        mNews.fields.thumbnail = "https://media.guim.co.uk/87fe4c83c0bf03d7b4183bf8a629538c3a635907/0_373_5212_3127/500.jpg";

        //When
        String actualImageUrl = mNews.getImageUrl();

        //Then
        assertEquals("https://media.guim.co.uk/87fe4c83c0bf03d7b4183bf8a629538c3a635907/0_373_5212_3127/500.jpg", actualImageUrl);
    }

    @Test
    public void getTitle() {
        //Given
        mNews.title = "Letter: Invoking the Rev Colin Morris law of TV debates";

        //When
        String actualTitle = mNews.getTitle();

        //Then
        assertEquals("Letter: Invoking the Rev Colin Morris law of TV debates", actualTitle);
    }

    @Test
    public void getShortText() {
        //Given
        mNews.fields = new AppNewsFields();
        mNews.fields.shortText = "<strong>Giles Oakley writes: </strong>When making a programme on the Sinn Féin ban, I was given wise counsel by the Rev Colin Morris";

        //When
        String actualShortText = mNews.getShortText();

        //Then
        assertEquals("<strong>Giles Oakley writes: </strong>When making a programme on the Sinn Féin ban, I was given wise counsel by the Rev Colin Morris", actualShortText);
    }

    @Test
    public void getDate() {
        //Given
        Date expectedDate = Calendar.getInstance().getTime();
        mNews.date = expectedDate;

        //When
        Date actualDate = mNews.getDate();

        //Then
        assertEquals(expectedDate, actualDate);
    }
}