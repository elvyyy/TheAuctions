package com.epam.auctions.exception;

public class UnknownRoleException extends RuntimeException {
    public UnknownRoleException() {
    }

    public UnknownRoleException(String message) {
        super(message);
    }
}
