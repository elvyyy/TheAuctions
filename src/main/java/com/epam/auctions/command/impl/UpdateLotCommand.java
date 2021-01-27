package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Page;
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

public class UpdateLotCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateLotCommand.class);

    private LotService lotService;

    public UpdateLotCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final int lotId = Integer.valueOf(context.getParameter("lotId"));
        final User user = (User) context.getSession().getAttribute("user");
        final Optional<Lot> lot = lotService.findById(lotId);
        if (lot.isPresent()) {
            if (lot.get().getCreatedBy() == user.getId()
                    || user.getUserRole() == UserRole.ADMIN) {

            }
        }
        return new CommandResult(ResponseType.REDIRECT, Page.EDIT_LOT_PAGE.getPage());
    }
}
