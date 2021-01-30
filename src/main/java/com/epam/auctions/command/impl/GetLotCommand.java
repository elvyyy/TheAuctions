package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Page;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetLotCommand implements Command {
    private LotService lotService;

    public GetLotCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final int lotId = Integer.parseInt(context.getParameter("lotId"));
        final Optional<Lot> lot = lotService.findById(lotId);

        return lot.map(l -> {
           return foo(l, context);
        }).orElseGet(() -> new CommandResult(ResponseType.REDIRECT, Route.FEED_PAGE.getRoute()));
    }

    public CommandResult foo(Lot l, HttpServletRequest context) {
        context.setAttribute("lot", l);
        lotService.getMaxBid(l.getId()).ifPresent(maxBid -> context.setAttribute("bid", maxBid));
        return new CommandResult(ResponseType.FORWARD, Page.LOT_PAGE.getPage());
    }
}
