package com.epam.framework.exception;

public class FrameworkClassCastException extends RuntimeException {
    public FrameworkClassCastException(String message) {
        super(message);
    }

    public FrameworkClassCastException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkClassCastException(Throwable cause) {
        super(cause);
    }

    public FrameworkClassCastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
