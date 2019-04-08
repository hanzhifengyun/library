package com.hanzhifengyun.library.base;

import com.hanzhifengyun.library.util.schedulers.ISchedulerProvider;

import javax.inject.Inject;

/**
 * BaseFragment for every Fragment in this application.
 */

public abstract class FengyunEmptyFragment extends FengyunBaseFragment<FengyunEmptyPresenter> implements FengyunEmptyContract.View {
    @Inject
    protected ISchedulerProvider mSchedulerProvider;
}
