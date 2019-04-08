package com.hanzhifengyun.base.base;


import com.hanzhifengyun.base.application.AndroidApplication;
import com.hanzhifengyun.base.di.components.ApplicationComponent;
import com.hanzhifengyun.base.di.components.DaggerFragmentComponent;
import com.hanzhifengyun.base.di.components.FragmentComponent;
import com.hanzhifengyun.base.di.modules.FragmentModule;
import com.hanzhifengyun.library.base.FengyunBaseDialogFragment;

public abstract class BaseDialogFragment<T extends BasePresenter> extends FengyunBaseDialogFragment<T> implements BaseView {

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
