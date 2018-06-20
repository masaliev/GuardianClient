package com.github.masaliev.guardianclient.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

public abstract class BaseMVVMActivity<T extends ViewDataBinding, V extends BaseViewModel> extends BaseActivity {
    private T mViewDataBinding;
    private V mViewModel;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDependencyInjection();
        performDataBinding();
        mViewModel.onViewCreated();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public abstract void performDependencyInjection();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    protected void onDestroy() {
        mViewModel.onDestroyView();
        super.onDestroy();
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();
}
