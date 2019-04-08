package com.hanzhifengyun.library.task;

import android.support.annotation.NonNull;

import com.hanzhifengyun.library.common.rx.FengyunBaseObserver;
import com.hanzhifengyun.library.util.TextUtil;
import com.hanzhifengyun.library.util.schedulers.ISchedulerProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.hanzhifengyun.library.util.Preconditions.checkNotNull;


@Singleton
public class FengyunTaskRepository {

    private ISchedulerProvider mSchedulerProvider;

    private Map<String, FengyunTask> mFengyunTaskList = new HashMap<>();
    /**
     * 优先进行的任务列表
     */
    private List<FengyunTask> mPreferentialFengyunTaskList = new ArrayList<>();
    /**
     * 稍后进行的任务列表
     */
    private List<FengyunTask> mLaterFengyunTaskList = new ArrayList<>();

    private Disposable mTimerFengyunTaskDisposable;

    private int taskCount;
    private int taskCompleteCount = 0;

    private Callback mCallback;


    private List<FengyunTask> mSuccessFengyunTaskList = new ArrayList<>();
    private List<FengyunTask> mFailureFengyunTaskList = new ArrayList<>();


    @Inject
    public FengyunTaskRepository(@NonNull ISchedulerProvider schedulerProvider) {
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");
    }


    public void addFengyunTask(FengyunTask task) {
        checkNotNull(task, "task cannot be null");
        if (mFengyunTaskList.containsKey(task.getTaskTag())) {
            return;
        }
        task.setCallback(new FengyunTask.Callback() {

            @Override
            public void onStart(FengyunTask task) {
                onFengyunTaskStart(task);
            }
            @Override
            public void onComplete(FengyunTask task) {
                onFengyunTaskComplete(task);
            }
        });
        mFengyunTaskList.put(task.getTaskTag(), task);
    }

    private void onFengyunTaskStart(FengyunTask task) {
        if (mCallback != null) {
            mCallback.onStart(task);
        }
    }

    private void onFengyunTaskComplete(FengyunTask task) {
        if (mCallback != null) {
            mCallback.onComplete(task);
        }
        taskCompleteCount++;
        if (task.isFailing()) {
            mFailureFengyunTaskList.add(task);
        } else {
            mSuccessFengyunTaskList.add(task);
        }

        checkLaterFengyunTask(task);

        checkAllComplete();
    }

    private void checkLaterFengyunTask(FengyunTask task) {
        for (FengyunTask laterFengyunTask : mLaterFengyunTaskList) {
            if (TextUtil.equals(task.getTaskTag(), laterFengyunTask.getTaskTag())) {
                if (task.isFailing()) {
                    //如果当前task失败了，则laterFengyunTask不执行，直接取消,递归下去
                    laterFengyunTask.cancel();
                } else {
                    //如果当前task成功了，则直接开始下个任务
                    laterFengyunTask.start();
                }
            }
        }
    }

    private void checkAllComplete() {
        if (isAllFengyunTaskComplete()) {
            if (mCallback != null) {
                FengyunTaskResult result = new FengyunTaskResult();
                result.setSuccessTaskList(mSuccessFengyunTaskList);
                result.setFailureTaskList(mFailureFengyunTaskList);
                mCallback.onAllComplete(result);
            }
        }
    }


    public void startAll() {
        startAll(null);
    }

    public void startAll(Callback callback) {
        resetAll();

        mCallback = callback;

        for (FengyunTask task : mPreferentialFengyunTaskList) {
            task.start();
        }
    }

    private void resetAll() {
        cancelAll();

        mSuccessFengyunTaskList.clear();
        mFailureFengyunTaskList.clear();
        mPreferentialFengyunTaskList.clear();
        mLaterFengyunTaskList.clear();
        taskCount = mFengyunTaskList.size();
        for (Map.Entry<String, FengyunTask> taskEntry : mFengyunTaskList.entrySet()) {
            FengyunTask task = taskEntry.getValue();
            if (task.isLater()) {
                mLaterFengyunTaskList.add(task);
            } else {
                mPreferentialFengyunTaskList.add(task);
            }
        }
        taskCompleteCount = 0;
    }


    private boolean isAllFengyunTaskComplete() {
        return taskCompleteCount == taskCount;
    }

    public void startTimerFengyunTask(long delay, long period) {
        startTimerFengyunTask(delay, period, null);
    }

    public void startTimerFengyunTask(long delay, long period, final Callback callback) {
        cancelTimerFengyunTask();

        getIntervalDelayObservable(delay, period)
                .subscribe(new FengyunBaseObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mTimerFengyunTaskDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        startAll(callback);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    public void cancelTimerFengyunTask() {
        if (mTimerFengyunTaskDisposable != null && !mTimerFengyunTaskDisposable.isDisposed()) {
            mTimerFengyunTaskDisposable.dispose();
        }
        cancelAll();
    }

    public void cancelAll() {
        for (Map.Entry<String, FengyunTask> taskEntry : mFengyunTaskList.entrySet()) {
            taskEntry.getValue().cancel();
        }
    }


    private Observable<Long> getIntervalDelayObservable(long delay, long period) {
        return Observable.interval(delay, period, TimeUnit.SECONDS)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.io());
    }

    public abstract static class Callback {

        public void onStart(FengyunTask task) {

        }

        public void onSuccess(FengyunTask task) {

        }

        public void onComplete(FengyunTask task) {

        }

        public abstract void onAllComplete(FengyunTaskResult result);
    }

}
