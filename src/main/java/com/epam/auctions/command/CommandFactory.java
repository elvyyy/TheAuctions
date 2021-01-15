package com.epam.auctions.command;

import com.epam.auctions.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
public enum CommandFactory {
    INSTANCE;

    public Command getCommand(HttpServletRequest context) {
        Optional<String> expectedCommand = Optional.ofNullable(context.getParameter(Constants.COMMAND.value()));
        String commandName = expectedCommand.orElseGet(CommandType.DEFAULT::name);
        try {
            return CommandType.valueOf(commandName.toUpperCase().replaceAll("-", "_")).getCommand();
        } catch (IllegalArgumentException e) {
            log.warn("There is no such command {}. Default command shall be returned", commandName, e);
            return CommandType.DEFAULT.getCommand();
        }
    }
}
