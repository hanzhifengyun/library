package com.hanzhifengyun.library.util.exception;


/**
 * 任务异常
 */
public class TaskException extends Exception {
    public TaskException() {

    }

    public TaskException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public TaskException(String exceptionMessage, Throwable throwable) {
        super(exceptionMessage, throwable);
    }

    public TaskException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "unknown error";
        }
        return super.getMessage();
    }
}
