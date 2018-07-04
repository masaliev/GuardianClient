package com.github.masaliev.guardianclient.ui.adapters;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.ui.base.BaseViewHolder;
import com.github.masaliev.guardianclient.utils.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsAdapterTest {

    private NewsAdapter mAdapter;

    @Before
    public void setUp() {
        mAdapter = spy(new NewsAdapter());
    }

    @Test
    public void onBindViewHolder() {
        //Given
        BaseViewHolder holder = mock(BaseViewHolder.class);

        //When
        mAdapter.onBindViewHolder(holder, 5);

        //Then
        verify(holder).onBind(5);
    }

    @Test
    public void getItemCount_itemsNull_returnsZero() throws Exception {
        //Given
        TestHelper.setPrivateFieldValue(mAdapter, "items", null);

        //When
        int actualItemCount = mAdapter.getItemCount();

        //Then
        assertEquals(0, actualItemCount);
    }

    @Test
    public void getItemCount_itemsNotNull_returnsCount() throws Exception {
        //Given
        List<News> newsList =  new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", newsList);

        //When
        int actualItemCount = mAdapter.getItemCount();

        //Then
        assertEquals(4, actualItemCount);
    }

    @Test
    public void setItems() throws Exception{
        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        doNothing().when(mAdapter).notifyDataSetChanged();

        //When
        mAdapter.setItems(newsList);

        //Then
        assertEquals(TestHelper.getPrivateFieldValue(mAdapter, "items"), newsList);
        verify(mAdapter).notifyDataSetChanged();
    }

    @Test
    public void setListener() throws Exception{
        //Given
        NewsAdapter.OnNewsClickListener listener = mock(NewsAdapter.OnNewsClickListener.class);

        //When
        mAdapter.setListener(listener);

        //Then
        assertEquals(TestHelper.getPrivateFieldValue(mAdapter, "mListener"), listener);
    }
}