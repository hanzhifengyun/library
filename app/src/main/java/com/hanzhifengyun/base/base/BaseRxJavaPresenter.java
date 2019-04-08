package com.hanzhifengyun.base.base;


import com.hanzhifengyun.library.base.FengyunBaseRxJavaPresenter;

/**
 * 便于管理所有Presenter的Disposable
 */
public abstract class BaseRxJavaPresenter<T extends BaseView> extends FengyunBaseRxJavaPresenter<T> implements BasePresenter<T> {

}
