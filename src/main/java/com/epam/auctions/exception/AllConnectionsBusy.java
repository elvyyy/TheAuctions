package com.epam.auctions.exception;

public class AllConnectionsBusy extends RuntimeException {
    public AllConnectionsBusy() {
    }

    public AllConnectionsBusy(String message) {
        super(message);
    }

    public AllConnectionsBusy(String message, Throwable cause) {
        super(message, cause);
    }

    public AllConnectionsBusy(Throwable cause) {
        super(cause);
    }

    public AllConnectionsBusy(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
