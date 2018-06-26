package com.github.masaliev.guardianclient.ui.splash;

import android.os.Bundle;
import android.widget.Toast;

import com.github.masaliev.guardianclient.App;
import com.github.masaliev.guardianclient.R;
import com.github.masaliev.guardianclient.BR;
import com.github.masaliev.guardianclient.databinding.ActivitySplashBinding;
import com.github.masaliev.guardianclient.ui.base.BaseMVVMActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseMVVMActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mViewModel;

    ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);

        mViewModel.upgradeSections();
    }

    @Override
    public void performDependencyInjection() {
        App.get(this).getComponent().inject(this);
    }

    @Override
    public SplashViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void goToMainPage() {
        Toast.makeText(this, "goToMainPage", Toast.LENGTH_SHORT).show();
    }
}
