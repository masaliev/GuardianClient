package com.github.masaliev.guardianclient.ui.filter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.android.databinding.library.baseAdapters.BR;
import com.github.masaliev.guardianclient.App;
import com.github.masaliev.guardianclient.R;
import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.databinding.ActivityFilterBinding;
import com.github.masaliev.guardianclient.ui.adapters.SectionAdapter;
import com.github.masaliev.guardianclient.ui.base.BaseMVVMActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

public class FilterActivity extends BaseMVVMActivity<ActivityFilterBinding, FilterViewModel>
        implements FilterNavigator {

    @Inject
    FilterViewModel mViewModel;
    ActivityFilterBinding mBinding;

    private SectionAdapter mAdapter;

    public static Intent getStartIntent(Context context, @Nullable Section section){
        Intent intent = new Intent(context, FilterActivity.class);
        if(section != null) {
            intent.putExtra("section", section);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);

        setUp();
    }

    private void setUp() {

        mAdapter = new SectionAdapter();
        mAdapter.setListener(section -> {
            Intent intent = new Intent();
            intent.putExtra("section", section);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);

        Section section = (Section) getIntent().getSerializableExtra("section");
        if(section != null){
            mAdapter.setSelectedSection(section);
        }

        mViewModel.getSections();

    }

    @Override
    public void performDependencyInjection() {
        App.get(this).getComponent().inject(this);
    }

    @Override
    public FilterViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    public void populateSectionList(List<? extends Section> sections) {
        mAdapter.setItems(sections);
    }
}
