package com.github.masaliev.guardianclient.ui.splash;

import com.github.masaliev.guardianclient.data.local.db.DatabaseHelper;
import com.github.masaliev.guardianclient.data.remote.repository.SectionRepository;
import com.github.masaliev.guardianclient.ui.base.BaseViewModel;
import com.github.masaliev.guardianclient.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator>{

    private final DatabaseHelper mDatabaseHelper;
    private final SectionRepository mSectionRepository;


    public SplashViewModel(SchedulerProvider schedulerProvider,
                           DatabaseHelper databaseHelper,
                           SectionRepository sectionRepository) {
        super(schedulerProvider);
        mDatabaseHelper = databaseHelper;
        mSectionRepository = sectionRepository;
    }

    void upgradeSections(){
        getCompositeDisposable().add(mSectionRepository.getSections()
                .doOnNext(mDatabaseHelper::upgradeSections)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                        sections -> getNavigator().goToMainPage(),
                        throwable -> getNavigator().goToMainPage()
                )
        );
    }

}
