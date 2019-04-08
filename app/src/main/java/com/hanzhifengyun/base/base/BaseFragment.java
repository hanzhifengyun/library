/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.hanzhifengyun.base.base;


import com.hanzhifengyun.base.application.AndroidApplication;
import com.hanzhifengyun.base.di.components.ApplicationComponent;
import com.hanzhifengyun.base.di.components.DaggerFragmentComponent;
import com.hanzhifengyun.base.di.components.FragmentComponent;
import com.hanzhifengyun.base.di.modules.FragmentModule;
import com.hanzhifengyun.library.base.FengyunBaseFragment;

/**
 * BaseFragment for every fragment in this application.
 */
public abstract class BaseFragment<T extends BasePresenter> extends FengyunBaseFragment<T> implements BaseView {

    protected ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getAndroidApplication().getApplicationComponent();
    }


    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }
}
