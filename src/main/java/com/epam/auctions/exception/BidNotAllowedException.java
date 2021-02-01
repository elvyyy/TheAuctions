package com.epam.auctions.exception;

public class BidNotAllowedException extends RuntimeException {
    public BidNotAllowedException() {
    }

    public BidNotAllowedException(String message) {
        super(message);
    }

    public BidNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BidNotAllowedException(Throwable cause) {
        super(cause);
    }
}
