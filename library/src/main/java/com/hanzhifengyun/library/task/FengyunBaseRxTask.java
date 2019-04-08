package com.hanzhifengyun.library.task;

public abstract class FengyunBaseRxTask extends FengyunTask {


    @Override
    protected void startTask() {
        cancelTask();
        run();
    }

    protected abstract void run();

    protected abstract void dispose();

    @Override
    protected void cancelTask() {
        dispose();
    }


}
