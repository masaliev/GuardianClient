package com.github.masaliev.guardianclient.ui.main;

import android.databinding.ObservableBoolean;

import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.data.remote.repository.NewsRepository;
import com.github.masaliev.guardianclient.ui.base.BaseViewModel;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final NewsRepository mNewsRepository;

    public ObservableBoolean isEmptyList = new ObservableBoolean(false);

    private Section mSelectedSection;

    private int nextPage = 1;
    private boolean hasNext = true;
    private boolean isLoading = false;

    public MainViewModel(SchedulerProvider schedulerProvider, NewsRepository newsRepository) {
        super(schedulerProvider);
        mNewsRepository = newsRepository;
    }

    public void getNews(){

        if(!hasNext || isLoading){
            return;
        }
        isLoading = true;
        getNavigator().showProgress();
        getCompositeDisposable().add(mNewsRepository.getNews(mSelectedSection, nextPage)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paginationResult -> {
                    getNavigator().hideProgress();
                    getNavigator().populateNewsList(paginationResult.results, paginationResult.currentPage == 1);
                    nextPage = paginationResult.currentPage + 1;
                    hasNext = paginationResult.currentPage < paginationResult.totalPages;
                    isLoading = false;
                }, throwable -> {
                    getNavigator().hideProgress();
                    getNavigator().handleError(throwable);
                    isLoading = false;
                }));
    }

    public void resetPagination(){
        nextPage = 1;
        hasNext = true;
    }

    public void setSelectedSection(Section section){
        mSelectedSection = section;
        resetPagination();
        getNews();
    }

    public Section getSelectedSection() {
        return mSelectedSection;
    }
}
