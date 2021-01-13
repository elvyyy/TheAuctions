package com.epam.auctions.command;

import com.epam.auctions.command.impl.SignupCommand;
import com.epam.auctions.service.impl.UserServiceImpl;

public enum CommandType {
    SIGNUP(new SignupCommand(UserServiceImpl.INSTANCE));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
