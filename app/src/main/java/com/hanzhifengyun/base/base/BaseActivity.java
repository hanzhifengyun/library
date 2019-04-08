package com.hanzhifengyun.base.base;

import android.os.Bundle;

import com.hanzhifengyun.base.application.AndroidApplication;
import com.hanzhifengyun.base.di.components.ActivityComponent;
import com.hanzhifengyun.base.di.components.ApplicationComponent;
import com.hanzhifengyun.base.di.components.DaggerActivityComponent;
import com.hanzhifengyun.base.di.modules.ActivityModule;
import com.hanzhifengyun.library.base.FengyunBaseActivity;


/**
 * BaseActivity for every Activity in this application.
 */
public abstract class BaseActivity<T extends BasePresenter> extends FengyunBaseActivity<T> implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getAndroidApplication().getApplicationComponent();
    }


    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
