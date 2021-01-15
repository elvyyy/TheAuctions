package com.epam.auctions.command;

import com.epam.auctions.command.impl.CheckUserExistingCommand;
import com.epam.auctions.command.impl.DefaultCommand;
import com.epam.auctions.command.impl.GetLoginCommand;
import com.epam.auctions.command.impl.GetSignUpCommand;
import com.epam.auctions.command.impl.SetLocaleCommand;
import com.epam.auctions.command.impl.SignupCommand;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.service.impl.UserServiceImpl;

public enum CommandType {
    GET_SIGNUP(new GetSignUpCommand()),
    GET_LOGIN(new GetLoginCommand()),
    SIGNUP(new SignupCommand(UserServiceImpl.INSTANCE)),
    DEFAULT(new DefaultCommand()),
    SET_LOCALE(new SetLocaleCommand()),
    EXISTS(new CheckUserExistingCommand(new UserRepository()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getCommandName() {
        return name();
    }
}
