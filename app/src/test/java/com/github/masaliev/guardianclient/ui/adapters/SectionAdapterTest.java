package com.github.masaliev.guardianclient.ui.adapters;

import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.ui.base.BaseViewHolder;
import com.github.masaliev.guardianclient.utils.TestHelper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SectionAdapterTest {

    private SectionAdapter mAdapter;

    @Before
    public void setUp() {
        mAdapter = spy(new SectionAdapter());
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
    public void getItemCount() throws Exception{
        //Given
        List<Section> sections = new ArrayList<>();
        sections.add(mock(Section.class));
        sections.add(mock(Section.class));
        sections.add(mock(Section.class));
        TestHelper.setPrivateFieldValue(mAdapter, "items", sections);

        //When
        int actualCount = mAdapter.getItemCount();

        //Then
        assertEquals(3, actualCount);
    }

    @Test
    public void setListener() throws Exception{
        //Given
        SectionAdapter.OnSectionClickListener listener = mock(SectionAdapter.OnSectionClickListener.class);

        //When
        mAdapter.setListener(listener);

        //Then
        assertEquals(TestHelper.getPrivateFieldValue(mAdapter, "mListener"), listener);
    }

    @Test
    public void setItems() throws Exception {
        //Given
        List<Section> sections = new ArrayList<>();
        sections.add(mock(Section.class));
        sections.add(mock(Section.class));
        sections.add(mock(Section.class));
        doNothing().when(mAdapter).notifyDataSetChanged();

        //When
        mAdapter.setItems(sections);

        //Then
        assertEquals(TestHelper.getPrivateFieldValue(mAdapter, "items"), sections);
        verify(mAdapter).notifyDataSetChanged();
    }
}