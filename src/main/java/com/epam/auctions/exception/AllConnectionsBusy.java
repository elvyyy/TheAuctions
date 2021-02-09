package com.epam.auctions.exception;

/**
 * The type All connections busy.
 */
public class AllConnectionsBusy extends RuntimeException {
    /**
     * Instantiates a new All connections busy.
     */
    public AllConnectionsBusy() {
    }

    /**
     * Instantiates a new All connections busy.
     *
     * @param message the message
     */
    public AllConnectionsBusy(String message) {
        super(message);
    }

    /**
     * Instantiates a new All connections busy.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AllConnectionsBusy(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new All connections busy.
     *
     * @param cause the cause
     */
    public AllConnectionsBusy(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new All connections busy.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public AllConnectionsBusy(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

