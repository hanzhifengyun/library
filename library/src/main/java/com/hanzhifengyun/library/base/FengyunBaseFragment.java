/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.hanzhifengyun.library.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanzhifengyun.library.R;
import com.hanzhifengyun.library.common.loading.FengyunOnceLoadingObserver;
import com.hanzhifengyun.library.common.rx.FengyunOnceObserver;
import com.hanzhifengyun.library.util.LogUtil;
import com.hanzhifengyun.library.util.rxbus.RxBusUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BaseFragment for every fragment in this application.
 */
public abstract class FengyunBaseFragment<T extends FengyunBasePresenter> extends Fragment implements FengyunBaseView {

    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;


    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    protected void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected abstract class OnceLoadingObserver<P> extends FengyunOnceLoadingObserver<P> {

        @Override
        public void onSubscribe(Disposable disposable) {
            super.onSubscribe(disposable);
            addDisposable(disposable);
        }

        @Override
        public void onError(Throwable throwable) {
            super.onError(throwable);
        }

        public OnceLoadingObserver(FengyunBaseView baseView, boolean cancelable) {
            super(baseView, cancelable);
        }

        public OnceLoadingObserver(FengyunBaseView baseView) {
            super(baseView);
        }
    }

    /**
     * 一次性观察者，无论回调哪个都结束观察
     */
    protected abstract class OnceObserver<Q> extends FengyunOnceObserver<Q> {
        @Override
        public void onSubscribe(Disposable disposable) {
            super.onSubscribe(disposable);
            addDisposable(disposable);
        }
    }



    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }


    @Override
    public void showNetWorkError() {
        showShortToast(getString(R.string.network_failure));
    }

    @Override
    public void showDataParseError() {
        showErrorToast(getString(R.string.data_parse_error));
    }


    @Override
    public void showMessage(String message) {
        showShortToast(message);
    }

    @Override
    public void showLoadingDialog(boolean cancelable) {
        showLoading(cancelable);
    }

    @Override
    public void hideLoadingDialog() {
        hideLoading();
    }

    @Override
    public void closeCurrentPage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {

            activity.finish();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), container, false);
        initInjector();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        mUnBinder = ButterKnife.bind(this, view);

        RxBusUtil.register(this);
        initEvent();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "--->onResume()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        dispose();
        RxBusUtil.unRegister(this);

    }


    protected void showLoading() {
        showLoading(true);
    }


    protected void showLoading(boolean cancelable) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showLoading(cancelable);
        }
    }

    private FengyunBaseActivity getBaseActivity() {
        return (FengyunBaseActivity) getActivity();
    }


    protected void hideLoading() {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().hideLoading();
        }
    }


    protected void showShortToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showShortToast(text);
        }
    }


    protected void showShortToast(int resId) {
        showShortToast(getString(resId));
    }

    protected void showRemindToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showRemindToast(text);
        }
    }

    protected void showRemindToast(int resId) {
        showRemindToast(getString(resId));
    }

    protected void showErrorToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showErrorToast(text);
        }
    }

    protected void showErrorToast(int resId) {
        showErrorToast(getString(resId));
    }

    private boolean isFromBaseActivityAndIsAdded() {
        return getActivity() != null && getActivity() instanceof FengyunBaseActivity && isAdded();
    }


    protected void showLongToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showLongToast(text);
        }
    }


    protected void showShortNewToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showShortNewToast(text);
        }
    }


    protected void showLongNewToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showLongNewToast(text);
        }
    }


    protected abstract void initInjector();

    protected abstract int getLayoutResId();

    protected abstract void initEvent();
}
