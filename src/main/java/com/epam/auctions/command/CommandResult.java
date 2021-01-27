package com.epam.auctions.command;

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

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }
}
