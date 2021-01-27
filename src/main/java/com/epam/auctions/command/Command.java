package com.epam.auctions.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Standard command interface.
 *  Every command must represent this interface.
 */
@FunctionalInterface
public interface Command {
    CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException;
}
