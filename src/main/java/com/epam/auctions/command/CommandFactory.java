package com.epam.auctions.command;

import com.epam.auctions.constant.Constants;
import com.epam.auctions.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public enum CommandFactory {
    INSTANCE;
    private static final Logger log = LoggerFactory.getLogger(CommandFactory.class);

    public Command getCommand(HttpServletRequest context) {
        return getCommandType(context).getCommand();
    }

    public CommandType getCommandType(HttpServletRequest request) {
        final String command = request.getParameter("command");
        log.debug("{}", command);
        Optional<String> expectedCommand = Optional.ofNullable(request.getParameter(Constants.COMMAND.value()));
        String commandName = expectedCommand.orElseGet(CommandType.DEFAULT::name);
        try {
            return CommandType.valueOf(commandName.toUpperCase().replaceAll("-", "_"));
        } catch (IllegalArgumentException e) {
            log.warn("There is no such command {}. Default command shall be returned", commandName, e);
            return CommandType.DEFAULT;
        }
    }
}
