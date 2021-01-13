package com.epam.auctions.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getCurrentRequestUrl(HttpServletRequest req) {
        String query = req.getQueryString();
        if (query == null) {
            return req.getRequestURI();
        } else {
            return req.getRequestURI() + "?" + query;
        }
    }
}
