package com.hanzhifengyun.library.common;


import android.support.annotation.NonNull;

import com.hanzhifengyun.library.common.loading.ILoadingView;
import com.hanzhifengyun.library.common.toast.IToastView;
import com.hanzhifengyun.library.di.ActivityScope;

import javax.inject.Inject;

import static com.hanzhifengyun.library.util.Preconditions.checkNotNull;


@ActivityScope
public class CommonViewRepository implements ILoadingView, IToastView {

    private final ILoadingView mLoadingView;

    private final IToastView mToastView;

    @Inject
    public CommonViewRepository(@NonNull ILoadingView loadingView,
                                @NonNull IToastView toastView) {
        mLoadingView = checkNotNull(loadingView, "loadingView can not be null");
        mToastView = checkNotNull(toastView, "toastView can not be null");
    }



    @Override
    public void showLoading(boolean cancelable) {
        mLoadingView.showLoading(cancelable);
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public boolean isShowing() {
        return mLoadingView.isShowing();
    }

    @Override
    public void showShortToast(CharSequence text) {
        mToastView.showShortToast(text);
    }

    @Override
    public void showRemindToast(CharSequence text) {
        mToastView.showRemindToast(text);
    }

    @Override
    public void showErrorToast(CharSequence text) {
        mToastView.showErrorToast(text);
    }

    @Override
    public void showLongToast(CharSequence text) {
        mToastView.showLongToast(text);
    }

    @Override
    public void showShortNewToast(CharSequence text) {
        mToastView.showShortNewToast(text);
    }

    @Override
    public void showLongNewToast(CharSequence text) {
        mToastView.showLongNewToast(text);
    }

}
