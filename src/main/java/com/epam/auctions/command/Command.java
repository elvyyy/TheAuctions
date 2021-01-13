package com.epam.auctions.command;

/**
 *  Standard command interface.
 *  Every command must represent this interface.
 */
@FunctionalInterface
public interface Command {
    CommandResult execute(RequestContext context);
}
