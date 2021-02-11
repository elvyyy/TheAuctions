package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Page;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.User;
import com.epam.auctions.repository.specification.LotCreatedBySpecification;
import com.epam.auctions.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class GetUserLotsCommand implements Command {
    private final LotService lotService;

    public GetUserLotsCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) context.getSession().getAttribute("user");
        final Integer userId = user.getId();
        final Collection<Lot> lots = lotService.selectAll(new LotCreatedBySpecification(), userId);
        context.setAttribute("lots", lots);
        return new CommandResult(ResponseType.FORWARD, Page.USER_LOTS.getPage());
    }
}
