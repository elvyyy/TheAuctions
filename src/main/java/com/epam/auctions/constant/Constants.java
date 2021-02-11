package com.epam.auctions.constant;

/**
 * The enum Constants.
 */
public enum Constants {
    /**
     * Command constants.
     */
    COMMAND("command"),
    /**
     * Command not found constants.
     */
    COMMAND_NOT_FOUND("commandNotFound"),
    /**
     * First name constants.
     */
    FIRST_NAME("first-name"),
    /**
     * Middle name constants.
     */
    MIDDLE_NAME("middle-name"),
    /**
     * Last name constants.
     */
    LAST_NAME("last-name"),
    /**
     * Username constants.
     */
    USERNAME("username"),
    /**
     * Email constants.
     */
    EMAIL("email"),
    /**
     * Password constants.
     */
    PASSWORD("password"),
    /**
     * Confirm password constants.
     */
    CONFIRM_PASSWORD("confirm-password"),
    /**
     * Language constants.
     */
    LANGUAGE("lang"),
    /**
     * User constants.
     */
    USER("user");

    /**
     * The constant LOTS_PER_PAGE.
     */
    public static final int LOTS_PER_PAGE = 10;

    /**
     * constant value
     */
    private final String value;

    Constants(final String value) {
        this.value = value;
    }

    /**
     * Value string.
     *
     * @return the string
     */
    public String value() {
        return value;
    }
}
