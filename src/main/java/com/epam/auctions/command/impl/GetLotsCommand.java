package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.constant.Page;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.repository.specification.LimitSpecification;
import com.epam.auctions.repository.specification.LotStatusSpecification;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.repository.specification.SqlSpecificationUtils;
import com.epam.auctions.repository.specification.WrapSpecification;
import com.epam.auctions.service.LotService;
import com.epam.auctions.service.impl.LotServiceImpl;
import com.epam.auctions.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class GetLotsCommand implements Command {
    private LotService lotService;

    public GetLotsCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException {
        final Collection<Lot> lots = lotService.selectAll(LotStatus.CREATED, 1, Constants.LOTS_PER_PAGE);
        context.setAttribute("lots", lots);
        final long totalCount = lotService.countAllLots(LotStatus.CREATED);
        context.setAttribute("pageCount", WebUtils.calculatePageCount(totalCount));
        return new CommandResult(ResponseType.FORWARD, Page.FEED_PAGE.getPage());
    }
}
