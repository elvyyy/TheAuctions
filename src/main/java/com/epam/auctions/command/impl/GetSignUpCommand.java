package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.context.Page;

import javax.servlet.http.HttpServletRequest;

public class GetSignUpCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest context) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, Page.SIGNUP_PAGE.getPage());
    }
}
