package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
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

public class UpdateUserRoleCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateUserRoleCommand.class);

    private final UserService userService;

    public UpdateUserRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        Boolean insertResult = false;
        try {
            String username = context.getParameter("username");
            UserRole role = UserRole.fromId(Integer.valueOf(context.getParameter("role")));
            final Optional<User> user = userService.findByUsername(username);
            insertResult = user.map(u -> {
                u.setUserRole(role);
                return userService.update(u);
            }).orElse(false);
        } catch (RuntimeException e) {
            LOG.error("Cannot update user role");
        }
        String jsonAnswer = getJsonBooleanResponse(insertResult);
        response.getWriter().write(jsonAnswer);
        return new CommandResult(ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(context));
    }

    private String getJsonBooleanResponse(boolean flag) {
        final JsonObject result;
        if (flag) {
            result = Json.createObjectBuilder()
                    .add("result", "ok")
                    .build();
        } else {
            result = Json.createObjectBuilder()
                    .add("result", "none")
                    .build();
        }
        return result.toString();
    }
}
