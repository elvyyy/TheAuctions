package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest context) {
        return new CommandResult(CommandResult.ResponseType.NO_ACTION, context.getRequestURI());
    }
}
