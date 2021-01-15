package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSignUpCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, Page.SIGNUP_PAGE.getPage());
    }
}
