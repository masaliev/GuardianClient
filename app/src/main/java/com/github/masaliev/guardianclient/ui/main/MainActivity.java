package com.github.masaliev.guardianclient.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.github.masaliev.guardianclient.App;
import com.github.masaliev.guardianclient.R;
import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.databinding.ActivityMainBinding;
import com.github.masaliev.guardianclient.ui.adapters.NewsAdapter;
import com.github.masaliev.guardianclient.ui.base.BaseMVVMActivity;
import com.github.masaliev.guardianclient.utils.EndlessRecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MainViewModel mViewModel;
    ActivityMainBinding mBinding;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);

        setUp();
    }

    private void setUp(){

        mBinding.swipeLayout.setOnRefreshListener(this);

        mAdapter = new NewsAdapter();
        mAdapter.setListener(news -> Toast.makeText(MainActivity.this, "onNewsClick: " + news.getTitle(), Toast.LENGTH_SHORT).show());

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mBinding.recyclerView.setAdapter(mAdapter);

        mBinding.recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager, 2) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mViewModel.getNews();
            }
        });

        mViewModel.getNews();
    }

    @Override
    public void performDependencyInjection() {
        App.get(this).getComponent().inject(this);
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showProgress() {
        mAdapter.setLoading(true);
    }

    @Override
    public void hideProgress() {
        mBinding.swipeLayout.setRefreshing(false);
        mAdapter.setLoading(false);
        super.hideProgress();

    }

    @Override
    public void populateNewsList(List<? extends News> newsList, boolean reset) {
        if(reset) {
            mAdapter.setItems(newsList);
        }else {
            mAdapter.addItems(newsList);
        }
    }


    @Override
    public void onRefresh() {
        mViewModel.resetPagination();
        mViewModel.getNews();
    }
}
