package com.epam.auctions.exception;

/**
 * The type Form validation exception.
 */
public class FormValidationException extends BusinessException {
    /**
     * Instantiates a new Form validation exception.
     */
    public FormValidationException() {
        super();
    }

    /**
     * Instantiates a new Form validation exception.
     *
     * @param message the message
     */
    public FormValidationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Form validation exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public FormValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Form validation exception.
     *
     * @param param the param
     * @param value the value
     */
    public FormValidationException(String param, String value) {
        super(String.format("Empty or incorrect \"%s\" parameter was found: %s", param, value));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
