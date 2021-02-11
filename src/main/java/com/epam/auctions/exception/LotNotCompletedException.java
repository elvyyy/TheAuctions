package com.epam.auctions.exception;

/**
 * Date: 2/9/21
 * Time: 6:52 PM
 *
 * @created by vlad
 */
public class LotNotCompletedException extends BusinessException {
    /**
     * Instantiates a new Lot not completed exception.
     */
    public LotNotCompletedException() {
    }

    /**
     * Instantiates a new Lot not completed exception.
     *
     * @param message the message
     */
    public LotNotCompletedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Lot not completed exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public LotNotCompletedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Lot not completed exception.
     *
     * @param cause the cause
     */
    public LotNotCompletedException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Lot not completed exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public LotNotCompletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
