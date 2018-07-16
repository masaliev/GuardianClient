package com.github.masaliev.guardianclient.ui.news_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.masaliev.guardianclient.App;
import com.github.masaliev.guardianclient.BR;
import com.github.masaliev.guardianclient.R;
import com.github.masaliev.guardianclient.databinding.ActivityNewsDetailsBinding;
import com.github.masaliev.guardianclient.ui.base.BaseMVVMActivity;

import javax.inject.Inject;

public class NewsDetailsActivity
        extends BaseMVVMActivity<ActivityNewsDetailsBinding, NewsDetailsViewModel>
        implements NewsDetailsNavigator{

    @Inject
    NewsDetailsViewModel mViewModel;
    private ActivityNewsDetailsBinding mBinding;

    public static Intent getStartIntent(Context context, String newsId){
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra("news_id", newsId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);

        setUp();
    }

    private void setUp() {
        String id = getIntent().getStringExtra("news_id");
        mViewModel.fetchNews(id);
    }

    @Override
    public void performDependencyInjection() {
        App.get(this).getComponent().inject(this);
    }

    @Override
    public NewsDetailsViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_details;
    }
}
