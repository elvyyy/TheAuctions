package com.epam.auctions.constant;

public enum Page {
    HOME_PAGE("/index.jsp"),
    SIGNUP_PAGE("/WEB-INF/jsp/signup.jsp"),
    LOGIN_PAGE("/WEB-INF/jsp/login.jsp"),
//    LOTS_PAGE("/WEB-INF/jsp/feed-lots.jsp"),
    FEED_PAGE("/WEB-INF/jsp/feed-lots.jsp"),
    CREATE_LOT_PAGE("/WEB-INF/jsp/create-lot-page.jsp"),
    NON_VERIFIED_LOTS("/WEB-INF/jsp/fragments/admin/non-verified-lots.jsp"),
    USER_LOTS("/WEB-INF/jsp/fragments/user/user-lots.jsp"),
    FEED_LOTS("/WEB-INF/jsp/fragments/lots.jsp"),
    PROFILE("/WEB-INF/jsp/fragments/profile.jsp"),
    ADMIN_PANEL("/WEB-INF/jsp/fragments/admin/panel.jsp"),
    EDIT_LOT_PAGE("/WEB-INF/jsp/fragments/edit-lot-page.jsp");

    private String page;

    Page(final String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
