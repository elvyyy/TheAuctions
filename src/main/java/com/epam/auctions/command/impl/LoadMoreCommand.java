package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.constant.Page;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.form.SearchForm;
import com.epam.auctions.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class LoadMoreCommand implements Command {
    private LotService lotService;

    public LoadMoreCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final SearchForm form = SearchForm.createFrom(context);
        final int page = Integer.valueOf(context.getParameter("page"));
        final Collection<Lot> lots = lotService.selectBySearchForm(form, page, Constants.LOTS_PER_PAGE);
        context.setAttribute("lots", lots);
        return new CommandResult(ResponseType.FORWARD, Page.FEED_LOTS.getPage());
    }
}
