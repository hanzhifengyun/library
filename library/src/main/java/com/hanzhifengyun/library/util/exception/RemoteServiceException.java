package com.hanzhifengyun.library.util.exception;

/**
 * 自定义异常
 */
public class RemoteServiceException extends Exception {

    private String code;

    public RemoteServiceException() {
    }

    public RemoteServiceException(String detailMessage) {
        super(detailMessage);
    }

    public RemoteServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RemoteServiceException(Throwable throwable) {
        super(throwable);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "unknown error";
        }
        return super.getMessage();
    }
}
