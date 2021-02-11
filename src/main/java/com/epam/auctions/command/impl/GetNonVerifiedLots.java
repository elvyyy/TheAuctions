package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Page;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.repository.specification.LotStatusSpecification;
import com.epam.auctions.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class GetNonVerifiedLots implements Command {
    private final LotService lotService;

    public GetNonVerifiedLots(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final Collection<Lot> lots = lotService.selectAll(new LotStatusSpecification(), LotStatus.CREATED.getId());
        context.getSession().setAttribute("lots", lots);
        return new CommandResult(ResponseType.FORWARD, Page.NON_VERIFIED_LOTS.getPage());
    }
}
