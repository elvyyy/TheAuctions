package com.epam.auctions.command;

public class CommandResult {
    private String page;
    /**
     * {@link ResponseType} response type
     */
    private ResponseType responseType;
    public CommandResult() {
    }

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public enum ResponseType {
        FORWARD,
        REDIRECT,
        NO_ACTION
    }
}
