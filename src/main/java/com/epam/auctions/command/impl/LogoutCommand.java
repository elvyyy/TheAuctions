package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException {
        context.getSession().invalidate();
        return new CommandResult(CommandResult.ResponseType.REDIRECT, Route.HOME_PAGE.getRoute());
    }
}
