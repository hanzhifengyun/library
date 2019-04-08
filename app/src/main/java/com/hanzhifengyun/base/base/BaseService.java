package com.hanzhifengyun.base.base;


import com.hanzhifengyun.base.application.AndroidApplication;
import com.hanzhifengyun.base.di.components.ApplicationComponent;
import com.hanzhifengyun.base.di.components.DaggerServiceComponent;
import com.hanzhifengyun.base.di.components.ServiceComponent;
import com.hanzhifengyun.library.base.FengyunBaseService;

public abstract class BaseService<T extends BasePresenter> extends FengyunBaseService<T> implements BaseView {

    private ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getAndroidApplication().getApplicationComponent();
    }


    protected ServiceComponent getServiceComponent() {
        return DaggerServiceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
    }
}
