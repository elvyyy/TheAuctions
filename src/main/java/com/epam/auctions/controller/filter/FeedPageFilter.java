package com.epam.auctions.controller.filter;

import com.epam.auctions.command.CommandFactory;
import com.epam.auctions.command.CommandType;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * The type Feed page filter.
 */
@WebFilter(filterName = "FeedPageFilter")
public class FeedPageFilter extends AbstractFilter {
    /**
     * Allowed {@link com.epam.auctions.command.Command}s for each {@link UserRole}
     */
    private final Map<CommandType, Set<UserRole>> allowedCommandRoles = new EnumMap<>(CommandType.class);

    /**
     * Initializes all allowed commands and its roles
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedCommandRoles.put(CommandType.GET_LOTS, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.GET_ADMIN_PANEL, EnumSet.of(UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.GET_CREATE_LOT, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.GET_USER_LOTS, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.GET_PROFILE, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.GET_NON_VERIFIED_LOTS, EnumSet.of(UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.CANCEL_LOT, EnumSet.of(UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.CONFIRM_LOT, EnumSet.of(UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.DEFAULT, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.EDIT_LOT, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.LOAD_MORE, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.SEARCH, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.SET_LOCALE, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.UPDATE_LOT, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.UPDATE_USER_PROFILE, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.UPDATE_USER_ROLE, EnumSet.of(UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.UPDATE_USER_STATUS, EnumSet.of(UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.CREATE_LOT, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.GET_LOT, EnumSet.of(UserRole.USER, UserRole.ADMIN));
        allowedCommandRoles.put(CommandType.MAKE_BID, EnumSet.of(UserRole.USER, UserRole.ADMIN));
    }

    /**
     * Checks whether user can use the command or cannot
     *
     * @param req         the req
     * @param resp        the resp
     * @param filterChain the filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        CommandType commandType = CommandFactory.INSTANCE.getCommandType(req);
        if (allowedCommandRoles.containsKey(commandType)) {
            final User user = (User) req.getSession().getAttribute("user");
            if (allowedCommandRoles.get(commandType).contains(user.getUserRole())) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect(Route.FEED_PAGE.getRoute());
            }
        } else {
            resp.sendRedirect(Route.FEED_PAGE.getRoute());
        }
    }
}
