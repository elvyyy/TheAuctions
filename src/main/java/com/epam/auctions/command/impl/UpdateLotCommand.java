package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.command.CommandType;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
import com.epam.auctions.service.LotService;
import com.epam.auctions.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class UpdateLotCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateLotCommand.class);

    private LotService lotService;

    public UpdateLotCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        try {
            final int lotId = Integer.valueOf(context.getParameter("lotId"));
            final User user = (User) context.getSession().getAttribute("user");
            final Optional<Lot> lot = lotService.findById(lotId);
            if (lot.isPresent() && lot.get().getLotStatus() == LotStatus.CREATED) {
                if (lot.get().getCreatedBy() == user.getId()
                        || user.getUserRole() == UserRole.ADMIN) {
                    final String title = context.getParameter("lot-title");
                    final String minBid = context.getParameter("minimal-bid");
                    final int duration = Integer.valueOf(context.getParameter("lot-time-duration"));

                    lot.get().setDescription(title);
                    lot.get().setMinimalBid(new BigDecimal(minBid));
                    lot.get().setDurationInMinutes(lotService.getDuration(duration));
                    lot.get().setVerifiedBy(user.getId());
                    lotService.update(lot.get());

                    final String query = WebUtils.getCurrentRequestUrl(context);
                    return new CommandResult(ResponseType.REDIRECT,
                            WebUtils.changeQueryCommand(query, CommandType.UPDATE_LOT, CommandType.EDIT_LOT));
                }
            }
        } catch (RuntimeException e) {
            LOG.error("something went wrong", e);
        }
        return new CommandResult(ResponseType.REDIRECT, Route.HOME_PAGE.getRoute());
    }
}
