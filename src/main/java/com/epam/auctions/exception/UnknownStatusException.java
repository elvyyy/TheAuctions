package com.epam.auctions.exception;

/**
 * The type Unknown status exception.
 * {@code UnknownStatusException} is an unchecked business exception.
 */
public class UnknownStatusException extends BusinessException {
    /**
     * Instantiates a new Unknown status exception.
     */
    public UnknownStatusException() {
    }

    /**
     * Instantiates a new Unknown status exception.
     *
     * @param message the message
     */
    public UnknownStatusException(String message) {
        super(message);
    }
}
