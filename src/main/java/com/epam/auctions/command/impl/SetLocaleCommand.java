package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandType;
import com.epam.auctions.context.Constants;
import com.epam.auctions.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SetLocaleCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest context) {
        Optional<String> expectedLanguage = Optional.ofNullable(context.getParameter(Constants.LANGUAGE.value()));
        String language = expectedLanguage.orElseGet(CommandType.DEFAULT::name);
        context.getSession().setAttribute("locale", language);
        return new CommandResult(CommandResult.ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(context));
    }
}
