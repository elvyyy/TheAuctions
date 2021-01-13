package com.epam.auctions.validator;

import lombok.Data;

@Data
public class Violation {
    private String message;

    public Violation() {
    }

    public Violation(String message) {
        this.message = message;
    }
}
