package com.epam.auctions.constant;

public enum Constants {
    COMMAND("command"),
    COMMAND_NOT_FOUND("commandNotFound"),
    FIRST_NAME("first-name"),
    MIDDLE_NAME("middle-name"),
    LAST_NAME("last-name"),
    USERNAME("username"),
    EMAIL("email"),
    PASSWORD("password"),
    CONFIRM_PASSWORD("confirm-password"),
    LANGUAGE("lang"),
    USER("user");

    public static final int LOTS_PER_PAGE = 10;

    private String value;

    Constants(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
