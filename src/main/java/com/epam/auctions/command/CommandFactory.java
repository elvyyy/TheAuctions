package com.epam.auctions.command;

import com.epam.auctions.context.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public enum CommandFactory {
    INSTANCE;

    public Command getCommand(RequestContext context) {
        Map<String, String[]> parameters = context.getRequestParameters();
        String commandType = parameters.containsKey(Constants.COMMAND.value())
                ? parameters.get(Constants.COMMAND.value())[0]
                : Constants.COMMAND_NOT_FOUND.value();

        try {
            return CommandType.valueOf(commandType.toUpperCase().replaceAll("-", "_")).getCommand();
        } catch (IllegalArgumentException e) {
            log.error("There is no such command {}", commandType, e);
            // TODO: return error command
            return CommandType.valueOf("ERROR").getCommand();
        }
    }
}
