package com.github.masaliev.guardianclient.ui.adapters;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.ui.base.BaseViewHolder;
import com.github.masaliev.guardianclient.utils.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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
    public void getItemCount_isLoadingFalse_returnsCount() throws Exception {
        //Given
        doNothing().when(mAdapter).notifyItemRemoved(anyInt());
        List<News> newsList =  new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", newsList);
        mAdapter.setLoading(false);

        //When
        int actualItemCount = mAdapter.getItemCount();

        //Then
        assertEquals(4, actualItemCount);
    }

    @Test
    public void getItemCount_isLoadingTrue_returnsCount() throws Exception {
        //Given
        doNothing().when(mAdapter).notifyItemInserted(anyInt());
        List<News> newsList =  new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", newsList);
        mAdapter.setLoading(true);

        //When
        int actualItemCount = mAdapter.getItemCount();

        //Then
        assertEquals(5, actualItemCount);
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
    public void addItems_emptyItems() throws Exception{
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", new ArrayList<>());
        doNothing().when(mAdapter).notifyItemRangeInserted(anyInt(), anyInt());


        //When
        mAdapter.addItems(newsList);

        //Then
        assertEquals(4, mAdapter.getItemCount());
        verify(mAdapter).notifyItemRangeInserted(0, newsList.size());
    }


    @Test
    public void addItems_notEmptyItems() throws Exception{
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", newsList);

        List<News> newNewsList = new ArrayList<>();
        newNewsList.add(mock(News.class));
        newNewsList.add(mock(News.class));
        newNewsList.add(mock(News.class));

        doNothing().when(mAdapter).notifyItemRangeInserted(anyInt(), anyInt());


        //When
        mAdapter.addItems(newNewsList);

        //Then
        assertEquals(7, mAdapter.getItemCount());
        verify(mAdapter).notifyItemRangeInserted(4, 3);
    }

    @Test
    public void getItemViewType() throws Exception{
        //Given
        List<News> newsList = new ArrayList<>();
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        newsList.add(mock(News.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", newsList);

        //When

        //Then
        assertEquals(2, mAdapter.getItemViewType(2));
        assertEquals(1, mAdapter.getItemViewType(4));
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