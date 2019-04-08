package com.hanzhifengyun.library.common.loading;


import com.google.gson.JsonParseException;
import com.hanzhifengyun.library.base.FengyunBaseView;
import com.hanzhifengyun.library.util.exception.RemoteServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 一次性接收者，封装BaseView loading and show errorMessage
 * 需在主线程调用，并且回调在主线程
 */
public abstract class FengyunOnceLoadingObserver<T> implements Observer<T> {
    private FengyunBaseView mBaseView;
    private boolean cancelable;
    private boolean isLoading;
    private Disposable mDisposable;

    public FengyunOnceLoadingObserver(FengyunBaseView baseView, boolean cancelable) {
        this.mBaseView = baseView;
        this.cancelable = cancelable;
    }

    public FengyunOnceLoadingObserver(FengyunBaseView baseView) {
        //默认可以取消
        this(baseView, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseView != null) {
            isLoading = true;
            mBaseView.showLoadingDialog(cancelable);
        }
        mDisposable = d;
    }


    @Override
    public void onError(Throwable e) {
        if (mDisposable == null || mDisposable.isDisposed()) {
            return;
        }
        if (mBaseView != null) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        if (e instanceof RemoteServiceException) {
            onResponseError(e);
        } else {
            onNetworkError(e);
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onNext(T value) {
        if (mDisposable == null || mDisposable.isDisposed()) {
            return;
        }
        if (mBaseView != null && isLoading) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        onResponse(value);
    }

    protected abstract void onResponse(T value);


    public void onResponseError(Throwable e) {
        if (mBaseView != null && e.getMessage() != null) {
            mBaseView.showMessage(e.getMessage());
        }
    }

    public void onNetworkError(Throwable e) {
        if (mBaseView != null) {
            if (e instanceof JsonParseException) {
                mBaseView.showDataParseError();
            } else {
                mBaseView.showNetWorkError();
            }
        }
    }

    @Override
    public void onComplete() {
        if (mDisposable == null || mDisposable.isDisposed()) {
            return;
        }
        if (mBaseView != null && isLoading) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
