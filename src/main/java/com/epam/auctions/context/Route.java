package com.epam.auctions.context;

public enum Route {
    HOME_PAGE("/index.jsp");

    private String route;

    Route(final String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
