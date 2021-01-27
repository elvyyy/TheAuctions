package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException {
        return new CommandResult(CommandResult.ResponseType.FORWARD, Page.LOGIN_PAGE.getPage());
    }
}
