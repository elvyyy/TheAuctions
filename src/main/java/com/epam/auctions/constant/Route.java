package com.epam.auctions.constant;

/**
 * The enum Route.
 */
public enum Route {
    /**
     * Home page route.
     */
    HOME_PAGE("/index.jsp"),
    /**
     * Signup page route.
     */
    SIGNUP_PAGE("/join?command=get-signup"),
    /**
     * Login page route.
     */
    LOGIN_PAGE("/join?command=get-login"),
    /**
     * Feed page route.
     */
    FEED_PAGE("/feed?command=get-lots"),
    /**
     * Create lot page route.
     */
    CREATE_LOT_PAGE("/feed?command=get-create-lot"),
    /**
     * User profile route.
     */
    USER_PROFILE("/feed?command=get-profile");

    /**
     * Route
     */
    private final String route;

    Route(final String route) {
        this.route = route;
    }

    /**
     * Gets route.
     *
     * @return the {@code route}
     */
    public String getRoute() {
        return route;
    }
}
