package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.User;
import com.epam.auctions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserProfile implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateUserProfile.class);

    private UserService userService;

    public UpdateUserProfile(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final User user = (User) context.getSession().getAttribute("user");
        try {
            populateUserWithUpdatedFields(context, user);
            userService.update(user);
        } catch (RuntimeException e) {
            LOG.error("Cannot update the profile", e);
        }
        return new CommandResult(ResponseType.REDIRECT, Route.USER_PROFILE.getRoute());
    }

    private void populateUserWithUpdatedFields(HttpServletRequest req, User user) {
        user.setFirstName(req.getParameter(Constants.FIRST_NAME.value()));
        user.setMiddleName(req.getParameter(Constants.MIDDLE_NAME.value()));
        user.setLastName(req.getParameter(Constants.LAST_NAME.value()));
        user.setUsername(req.getParameter(Constants.USERNAME.value()));
    }
}
