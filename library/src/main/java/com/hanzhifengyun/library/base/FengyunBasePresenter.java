package com.hanzhifengyun.library.base;


/**
 * Presenter基类
 */
public interface FengyunBasePresenter<T extends FengyunBaseView>{

    void attachView(T view);

    void detachView();

    void onStart();
}