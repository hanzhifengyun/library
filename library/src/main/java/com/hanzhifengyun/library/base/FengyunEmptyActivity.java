package com.hanzhifengyun.library.base;


import com.hanzhifengyun.library.util.schedulers.ISchedulerProvider;

import javax.inject.Inject;

/**
 * BaseActivity for every Activity in this application.
 */

public abstract class FengyunEmptyActivity extends FengyunBaseActivity<FengyunEmptyPresenter> implements FengyunEmptyContract.View {
    @Inject
    protected ISchedulerProvider mSchedulerProvider;
}
