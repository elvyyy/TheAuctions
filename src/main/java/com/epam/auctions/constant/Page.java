package com.epam.auctions.constant;

public enum Page {
    HOME_PAGE("/index.jsp"),
    SIGNUP_PAGE("/WEB-INF/jsp/signup.jsp"),
    LOGIN_PAGE("/WEB-INF/jsp/login.jsp");

    private String page;

    Page(final String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
