package com.epam.auctions.context;

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
    LANGUAGE("lang");


    private String command;

    Constants(final String command) {
        this.command = command;
    }

    public String value() {
        return command;
    }
}
