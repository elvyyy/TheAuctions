package com.epam.auctions.controller.filter;

import com.epam.auctions.command.CommandFactory;
import com.epam.auctions.command.CommandType;
import com.epam.auctions.constant.Route;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

/**
 * The type Join page filter.
 */
@WebFilter(filterName = "JoinPageFilter")
public class JoinPageFilter extends AbstractFilter {
    /**
     * allowed commands for each {@link com.epam.auctions.entity.UserRole}
     */
    private final Set<CommandType> joinPageAllowedCommands = EnumSet.of(
            CommandType.GET_SIGNUP, CommandType.LOGIN,
            CommandType.LOGOUT, CommandType.GET_LOGIN,
            CommandType.EXISTS, CommandType.SET_LOCALE,
            CommandType.SIGNUP);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Checks whether user can use command or not
     *
     * @param req         the req
     * @param resp        the resp
     * @param filterChain the filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        final CommandType commandType = CommandFactory.INSTANCE.getCommandType(req);

        if (joinPageAllowedCommands.contains(commandType)) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(Route.LOGIN_PAGE.getRoute());
        }
    }
}
