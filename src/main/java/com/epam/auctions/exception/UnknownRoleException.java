package com.epam.auctions.exception;

/**
 * The type Unknown role exception.
 * {@code UnknownRoleException} is an unchecked business exception
 */
public class UnknownRoleException extends BusinessException {
    /**
     * Instantiates a new Unknown role exception.
     */
    public UnknownRoleException() {
    }

    /**
     * Instantiates a new Unknown role exception.
     *
     * @param message the message
     */
    public UnknownRoleException(String message) {
        super(message);
    }
}
