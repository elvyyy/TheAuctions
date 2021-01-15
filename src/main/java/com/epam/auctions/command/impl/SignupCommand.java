package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;
import com.epam.auctions.entity.User;
import com.epam.auctions.service.UserService;
import com.epam.auctions.validator.Validator;
import com.epam.auctions.validator.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SignupCommand implements Command {
    private UserService userService;

    public SignupCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) {
        Validator<User> validator = ValidatorFactory.getValidator(User.class);

        User user = null;
        try {
            user = validator.validate(context);
            User registeredUser = userService.register(user);
            log.info("{}", registeredUser);
        } catch (Exception e) {
            log.error("Cannot create user {}", user, e);
            return new CommandResult(CommandResult.ResponseType.FORWARD, Page.SIGNUP_PAGE.getPage());
        }
        return new CommandResult(CommandResult.ResponseType.REDIRECT, Page.HOME_PAGE.getPage());
    }
}
