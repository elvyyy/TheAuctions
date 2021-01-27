package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.User;
import com.epam.auctions.service.UserService;
import com.epam.auctions.service.impl.ServiceManager;
import com.epam.auctions.service.impl.UserServiceImpl;
import com.epam.auctions.validator.Validator;
import com.epam.auctions.validator.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(SignupCommand.class);

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
        return new CommandResult(CommandResult.ResponseType.REDIRECT, Route.HOME_PAGE.getRoute());
    }
}
