package com.epam.auctions.exception;

/**
 * {@code BidNotAllowedException} could be thrown if bid operations could not be performed
 */
public class BidNotAllowedException extends BusinessException {
    /**
     * Instantiates a new Bid not allowed exception.
     */
    public BidNotAllowedException() {
        super();
    }

    /**
     * Instantiates a new Bid not allowed exception.
     *
     * @param message the message
     */
    public BidNotAllowedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Bid not allowed exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BidNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}

