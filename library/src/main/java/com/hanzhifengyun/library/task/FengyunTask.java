package com.hanzhifengyun.library.task;


import com.hanzhifengyun.library.util.exception.TaskException;

public abstract class FengyunTask {

    public void start() {
        if (isStarting) {
            cancel();
        }
        onTaskStart();
        startTask();
    }

    /**
     * 开始任务
     */
    protected abstract void startTask();

    public void cancel() {
        if (isStarting) {
            isStarting = false;
            cancelTask();
        }
        onTaskComplete(new TaskException("cancel"));
    }

    /**
     * 取消任务
     */
    protected abstract void cancelTask();

    /**
     * @return 任务名称
     */
    public abstract String getName();

    /**
     * @return 任务标识
     */
    public abstract String getTaskTag();

    /**
     * @return 是否是稍后进行的任务, 返回 true 时需要实现{@link #getPreviousTaskTag()}
     */
    public abstract boolean isLater();

    /**
     * @return 之前任务的任务标识，只有当{@link #isLater()} 返回 true 时需要实现
     * 如果没有找到对应的任务，则此任务不会执行
     */
    public String getPreviousTaskTag(){
        return null;
    }


    public boolean isStarting() {
        return isStarting;
    }


    private boolean isStarting;

    private Throwable mTaskError;

    private Callback mCallback;

    public FengyunTask setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    protected void onTaskStart() {
        mTaskError = null;
        if (mCallback != null) {
            mCallback.onStart(this);
        }
    }

    protected void onTaskComplete(Throwable e) {
        isStarting = false;
        mTaskError = e;
        if (mCallback != null) {
            mCallback.onComplete(this);
        }
    }

    protected void onTaskComplete() {
        onTaskComplete(null);
    }

    public Throwable getTaskError() {
        return mTaskError;
    }

    /**
     * @return 是否失败的
     */
    public boolean isFailing() {
        return mTaskError != null;
    }

    public interface Callback {

        void onStart(FengyunTask task);

        void onComplete(FengyunTask task);
    }

}

