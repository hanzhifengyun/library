package com.hanzhifengyun.base.di.components;

import android.app.Activity;

import com.hanzhifengyun.base.di.modules.FragmentModule;
import com.hanzhifengyun.library.di.FragmentScope;

import dagger.Component;


@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
}
