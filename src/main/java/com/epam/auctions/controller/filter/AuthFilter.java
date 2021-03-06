package com.epam.auctions.controller.filter;

import com.epam.auctions.constant.Constants;
import com.epam.auctions.constant.Route;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Auth filter.
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter extends AbstractFilter {

    /**
     * Checked whether user is sign in or not
     *
     * @param req         the request
     * @param resp        the response
     * @param filterChain the filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(Constants.USER.value()) == null) {
            resp.sendRedirect(Route.HOME_PAGE.getRoute());
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
