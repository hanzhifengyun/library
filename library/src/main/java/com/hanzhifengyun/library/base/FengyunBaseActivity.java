package com.hanzhifengyun.library.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanzhifengyun.library.R;
import com.hanzhifengyun.library.common.CommonViewRepository;
import com.hanzhifengyun.library.common.loading.FengyunOnceLoadingObserver;
import com.hanzhifengyun.library.common.rx.FengyunOnceObserver;
import com.hanzhifengyun.library.util.LogUtil;
import com.hanzhifengyun.library.util.rxbus.RxBusUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * BaseActivity for every Activity in this application.
 */
public abstract class FengyunBaseActivity<T extends FengyunBasePresenter> extends AppCompatActivity implements FengyunBaseView {

    protected final String TAG = this.getClass().getSimpleName();
    /**
     * 当该页面不需要布局时 return
     */
    protected static final int LAYOUT_ID_NULL = 0;


    @Inject
    protected T mPresenter;

    private Unbinder mUnBinder;

    protected Activity mContext;

    @Inject
    Lazy<CommonViewRepository> mCommonViewRepositoryLazy;

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
    public void showNetWorkError() {
        showErrorToast(getString(R.string.network_failure));
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
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        int layoutResId = getLayoutResId();
        if (layoutResId != LAYOUT_ID_NULL) {
            setContentView(layoutResId);

            mUnBinder = ButterKnife.bind(this);
        }

        initInjector();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initEvent();

        RxBusUtil.register(this);

        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG, "--->onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        dispose();

        RxBusUtil.unRegister(this);

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    protected void showLoading() {
        showLoading(true);
    }


    protected void showLoading(boolean cancelable) {
        if (!isFinishing() && mCommonViewRepositoryLazy != null) {
            getCommonViewRepositoryLazy().showLoading(cancelable);
        }
    }

    private CommonViewRepository getCommonViewRepositoryLazy() {
        return mCommonViewRepositoryLazy.get();
    }


    protected void hideLoading() {
        if (!isFinishing() && mCommonViewRepositoryLazy != null) {
            getCommonViewRepositoryLazy().hideLoading();
        }
    }


    public void showShortToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showShortToast(text);
        }
    }


    protected void showShortToast(int resId) {
        showShortToast(getString(resId));
    }

    public void showRemindToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showRemindToast(text);
        }
    }

    protected void showRemindToast(int resId) {
        showRemindToast(getString(resId));
    }

    public void showErrorToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showErrorToast(text);
        }
    }

    protected void showErrorToast(int resId) {
        showErrorToast(getString(resId));
    }


    protected void showLongToast(int resId) {
        showLongToast(getString(resId));
    }


    protected void showLongToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showLongToast(text);
        }
    }


    protected void showShortNewToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showShortNewToast(text);
        }
    }


    protected void showLongNewToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showLongNewToast(text);
        }
    }

    protected <T extends View> T findById(@NonNull View view, @IdRes int resId) {
        return ButterKnife.findById(view, resId);
    }


    protected abstract void initInjector();

    protected abstract int getLayoutResId();

    protected abstract void initEvent();
}
