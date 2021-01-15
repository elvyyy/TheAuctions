package com.epam.auctions.util;

import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    public static String getCurrentRequestUrl(HttpServletRequest req) {
        String query = req.getQueryString();
        if (query == null) {
            return req.getRequestURI();
        } else {
            return req.getRequestURI() + "?" + query;
        }
    }

    public static void processRouteRequest(CommandResult result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRouteRequest(result, req, resp, Page.HOME_PAGE);
    }

    public static void processRouteRequest(CommandResult result, HttpServletRequest req, HttpServletResponse resp, Page defaultPage) throws ServletException, IOException {
        String page = result.getPage();
        switch (result.getResponseType()) {
            case FORWARD: {
                req.getRequestDispatcher(page).forward(req, resp);
                break;
            }
            case REDIRECT: {
                resp.sendRedirect(page);
                break;
            }
            case NO_ACTION: {
                if (defaultPage == Page.HOME_PAGE) {
                    resp.sendRedirect(defaultPage.getPage());
                    break;
                }
                //req.getRequestDispatcher(defaultPage.getPage()).forward(req, resp);
                break;
            }
        }
    }
}
