package com.epam.auctions.util;

import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.command.CommandType;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.constant.Page;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Web utils.
 */
public class WebUtils {

    /**
     * Calculates page count int.
     *
     * @param totalCount the total count
     * @return the int
     */
    public static int calculatePageCount(long totalCount) {
        int pageCount = (int) (totalCount / Constants.LOTS_PER_PAGE);
        if (pageCount * Constants.LOTS_PER_PAGE != totalCount) {
            pageCount++;
        }
        return pageCount;
    }

    /**
     * Change query command string.
     *
     * @param query   the query
     * @param replace the replace
     * @param type    the type
     * @return the string
     */
    public static String changeQueryCommand(String query, CommandType replace, CommandType type) {
        return query
                .replaceFirst(replace.getCommandName().replaceAll("_", "-").toLowerCase(),
                        type.getCommandName().replaceAll("_", "-").toLowerCase());
    }

    /**
     * Process route request.
     *
     * @param result the result
     * @param req    the req
     * @param resp   the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public static void processRouteRequest(CommandResult result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRouteRequest(result, req, resp, Page.HOME_PAGE);
    }

    /**
     * Process route request.
     *
     * @param result      the result
     * @param req         the req
     * @param resp        the resp
     * @param defaultPage the default page
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
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
                break;
            }
        }
    }

    /**
     * Response fail command result.
     *
     * @param req  the req
     * @param resp the resp
     * @return the command result
     * @throws IOException the io exception
     */
    public static CommandResult responseFail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String jsonResponse = Json.createObjectBuilder()
                .add("result", "none").build().toString();
        resp.getWriter().write(jsonResponse);
        return new CommandResult(ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(req));
    }

    /**
     * Responses ok command result.
     *
     * @param req  the req
     * @param resp the resp
     * @return the command result
     * @throws IOException the io exception
     */
    public static CommandResult responseOk(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String jsonResponse = Json.createObjectBuilder()
                .add("result", "ok").build().toString();
        resp.getWriter().write(jsonResponse);
        return new CommandResult(ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(req));
    }

    /**
     * Gets current request url.
     *
     * @param req the req
     * @return the current request url
     */
    public static String getCurrentRequestUrl(HttpServletRequest req) {
        String query = req.getQueryString();
        if (query == null) {
            return req.getRequestURI();
        } else {
            return req.getRequestURI() + "?" + query;
        }
    }
}
