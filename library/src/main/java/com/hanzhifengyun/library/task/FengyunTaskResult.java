package com.hanzhifengyun.library.task;

import java.util.ArrayList;
import java.util.List;

public class FengyunTaskResult {

    private List<FengyunTask> mSuccessTaskList = new ArrayList<>();
    private List<FengyunTask> mFailureTaskList = new ArrayList<>();

    public boolean isAllSuccess() {
        return mFailureTaskList.isEmpty();
    }

    public List<FengyunTask> getSuccessTaskList() {
        return mSuccessTaskList;
    }

    public void setSuccessTaskList(List<FengyunTask> successTaskList) {
        mSuccessTaskList.clear();
        mSuccessTaskList.addAll(successTaskList);
    }

    public List<FengyunTask> getFailureTaskList() {
        return mFailureTaskList;
    }

    public void setFailureTaskList(List<FengyunTask> failureTaskList) {
        mFailureTaskList.clear();
        mFailureTaskList.addAll(failureTaskList);
    }
}
