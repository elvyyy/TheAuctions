package com.epam.auctions.exception;

/**
 * Date: 2/10/21
 * Time: 6:32 PM
 *
 * @created by vlad
 */
public class TransactionalException extends RuntimeException {
    /**
     * Instantiates a new Transactional exception.
     */
    public TransactionalException() {
    }

    /**
     * Instantiates a new Transactional exception.
     *
     * @param message the message
     */
    public TransactionalException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Transactional exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public TransactionalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Transactional exception.
     *
     * @param cause the cause
     */
    public TransactionalException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Transactional exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public TransactionalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
