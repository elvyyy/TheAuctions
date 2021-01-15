package com.epam.auctions.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandResult {
    public enum ResponseType {
        FORWARD,
        REDIRECT,
        NO_ACTION
    }
    /**
     * {@link ResponseType} response type
     */
    private ResponseType responseType;
    private String page;

    public CommandResult() {
    }

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }
}
