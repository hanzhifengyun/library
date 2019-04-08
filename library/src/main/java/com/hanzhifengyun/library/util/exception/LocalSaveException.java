package com.hanzhifengyun.library.util.exception;


/**
 * 本地保存异常
 */
public class LocalSaveException extends Exception {
    public LocalSaveException() {

    }

    public LocalSaveException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public LocalSaveException(String exceptionMessage, Throwable throwable) {
        super(exceptionMessage, throwable);
    }

    public LocalSaveException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "save failure";
        }
        return super.getMessage();
    }
}
