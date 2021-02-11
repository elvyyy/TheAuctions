package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Page;
import com.epam.auctions.constant.Route;
import com.epam.auctions.service.LotService;
import com.epam.auctions.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLotCommand implements Command {
    private final LotService lotService;
    private final UserService userService;

    public GetLotCommand(LotService lotService, UserService userService) {
        this.lotService = lotService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        try {
            final int lotId = Integer.parseInt(context.getParameter("l"));
            return lotService
                    .findById(lotId)
                    .map(lot -> {
                        context.setAttribute("lot", lot);
                        //userService.findById(lot.getId());
                        lotService.getMaxBid(lot.getId()).ifPresent(maxBid -> context.setAttribute("bid", maxBid));
                        return new CommandResult(ResponseType.FORWARD, Page.LOT_PAGE.getPage());
                    }).orElseGet(() -> new CommandResult(ResponseType.REDIRECT, Route.FEED_PAGE.getRoute()));
        } catch (RuntimeException e) {
            return new CommandResult(ResponseType.REDIRECT, Route.FEED_PAGE.getRoute());
        }
    }
}
