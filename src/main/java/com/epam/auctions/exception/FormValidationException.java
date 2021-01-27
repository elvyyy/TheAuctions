package com.epam.auctions.exception;

public class FormValidationException extends RuntimeException {
    public FormValidationException() {
        super();
    }

    public FormValidationException(String message) {
        super(message);
    }

    public FormValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormValidationException(String param, String value) {
        super(String.format("Empty or incorrect \"%s\" parameter was found: %s", param, value));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
