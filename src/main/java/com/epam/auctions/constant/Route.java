package com.epam.auctions.constant;

public enum Route {
    HOME_PAGE("/index.jsp"),
    SIGNUP_PAGE("/join?command=get-signup"),
    LOGIN_PAGE("/join?command=get-login"),
    FEED_PAGE("/feed?command=get-lots"),
    CREATE_LOT_PAGE("/feed?command=get-create-lot"),
    USER_PROFILE("/feed?command=get-profile");

    private String route;

    Route(final String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
