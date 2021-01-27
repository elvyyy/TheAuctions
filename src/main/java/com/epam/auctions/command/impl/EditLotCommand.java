package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Page;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
import com.epam.auctions.service.LotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class EditLotCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(EditLotCommand.class);

    private LotService lotService;

    public EditLotCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        int lotId = Integer.valueOf(context.getParameter("lotId"));
        final User user = (User) context.getSession().getAttribute("user");

        final Optional<Lot> lot = lotService.findById(lotId);
        if (lot.isPresent()) {
            final Integer createdById = lot.get().getCreatedBy();
            if (user.getId().equals(createdById) || user.getUserRole() == UserRole.ADMIN) {
                context.setAttribute("lot", lot.get());
                return new CommandResult(ResponseType.FORWARD, Page.EDIT_LOT_PAGE.getPage());
            }
        }
        return new CommandResult(ResponseType.REDIRECT, Route.HOME_PAGE.getRoute());
    }
}
