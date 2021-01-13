package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.RequestContext;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
import com.epam.auctions.entity.UserStatus;
import com.epam.auctions.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.epam.auctions.context.Constants.CONFIRM_PASSWORD;
import static com.epam.auctions.context.Constants.EMAIL;
import static com.epam.auctions.context.Constants.FIRST_NAME;
import static com.epam.auctions.context.Constants.LAST_NAME;
import static com.epam.auctions.context.Constants.MIDDLE_NAME;
import static com.epam.auctions.context.Constants.PASSWORD;
import static com.epam.auctions.context.Constants.USERNAME;

@Slf4j
public class SignupCommand implements Command {
    private UserService userService;

    public SignupCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext context) {
        Map<String, String[]> parameters = context.getRequestParameters();
        String firstName = context.getParameter(FIRST_NAME.value());
        String middleName = context.getParameter(MIDDLE_NAME.value());
        String lastName = context.getParameter(LAST_NAME.value());
        String username = context.getParameter(USERNAME.value());
        String email = context.getParameter(EMAIL.value());
        String password = context.getParameter(PASSWORD.value());
        String confirmPassword = context.getParameter(CONFIRM_PASSWORD.value());

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setUserRole(UserRole.USER);
        user.setUserStatus(UserStatus.REGISTERED);

        User registeredUser = userService.register(user);
        log.info("{}", registeredUser);

        String targetRoute = "/feed";

//        log.info("First name {}, middle name {}", firstName[0], middleName[0]);
        return new CommandResult(CommandResult.ResponseType.REDIRECT, targetRoute);
    }
}
