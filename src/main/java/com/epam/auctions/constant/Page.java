package com.epam.auctions.constant;

/**
 * The enum Page.
 */
public enum Page {
    /**
     * Home page page.
     */
    HOME_PAGE("/index.jsp"),
    /**
     * Signup page page.
     */
    SIGNUP_PAGE("/WEB-INF/jsp/signup.jsp"),
    /**
     * Login page page.
     */
    LOGIN_PAGE("/WEB-INF/jsp/login.jsp"),
    /**
     * Feed page page.
     */
    FEED_PAGE("/WEB-INF/jsp/feed-lots.jsp"),
    /**
     * Create lot page page.
     */
    CREATE_LOT_PAGE("/WEB-INF/jsp/create-lot-page.jsp"),
    /**
     * Non verified lots page.
     */
    NON_VERIFIED_LOTS("/WEB-INF/jsp/fragments/admin/non-verified-lots.jsp"),
    /**
     * User lots page.
     */
    USER_LOTS("/WEB-INF/jsp/fragments/user/user-lots.jsp"),
    /**
     * Feed lots page.
     */
    FEED_LOTS("/WEB-INF/jsp/fragments/lots.jsp"),
    /**
     * Profile page.
     */
    PROFILE("/WEB-INF/jsp/fragments/profile.jsp"),
    /**
     * Admin panel page.
     */
    ADMIN_PANEL("/WEB-INF/jsp/fragments/admin/panel.jsp"),
    /**
     * Edit lot page page.
     */
    EDIT_LOT_PAGE("/WEB-INF/jsp/fragments/edit-lot-page.jsp"),
    /**
     * Lot page page.
     */
    LOT_PAGE("/WEB-INF/jsp/fragments/lot-page.jsp");

    /**
     * constant page
     */
    private final String page;

    Page(final String page) {
        this.page = page;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }
}
