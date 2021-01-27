package com.epam.auctions.exception;

public class UnknownStatusException extends RuntimeException {
    public UnknownStatusException() {
    }

    public UnknownStatusException(String message) {
        super(message);
    }
}
