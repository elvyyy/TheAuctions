package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserStatus;
import com.epam.auctions.service.UserService;
import com.epam.auctions.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UpdateUserStatusCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateUserStatusCommand.class);

    private UserService userService;

    public UpdateUserStatusCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        Boolean insertResult;
        try {
            String username = context.getParameter("username");
            UserStatus status = UserStatus.fromId(Integer.valueOf(context.getParameter("status")));
            final Optional<User> user = userService.findByUsername(username);
            insertResult = user.map(u -> {
                u.setUserStatus(status);
                return userService.update(u);
            }).orElse(false);
        } catch (RuntimeException e) {
            LOG.error("Cannot update user status");
            insertResult = false;
        }
        final JsonObject result;
        if (insertResult) {
            result = Json.createObjectBuilder()
                    .add("result", "ok")
                    .build();
        } else {
            result = Json.createObjectBuilder()
                    .add("result", "none")
                    .build();
        }
        response.getWriter().write(result.toString());
        return new CommandResult(ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(context));
    }
}
