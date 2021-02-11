package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.constant.Route;
import com.epam.auctions.service.UserService;
import com.epam.auctions.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);
    private final UserService userService = UserServiceImpl.INSTANCE;

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) {
        String email = context.getParameter(Constants.EMAIL.value());
        String password = context.getParameter(Constants.PASSWORD.value());

        CommandResult result = userService.
                findByEmail(email)
                .map(user -> {
                    if (BCrypt.checkpw(password, user.getPassword())) {
                        LOG.debug("Вход выполнен успешно");
                        context.getSession().setAttribute("user", user);
                        return new CommandResult(CommandResult.ResponseType.REDIRECT, Route.FEED_PAGE.getRoute());
                    } else {
                        LOG.debug("Пароль введен неверно");
                        return new CommandResult(CommandResult.ResponseType.REDIRECT, Route.LOGIN_PAGE.getRoute());
                    }
                })
                .orElseGet(() -> {
                    LOG.debug("Пользователь не найден");
                    return new CommandResult(CommandResult.ResponseType.REDIRECT, Route.LOGIN_PAGE.getRoute());
                });
        return result;
    }
}
