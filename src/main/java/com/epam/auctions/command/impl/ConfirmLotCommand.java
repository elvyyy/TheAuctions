package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.service.AuctionService;
import com.epam.auctions.service.LotService;
import com.epam.auctions.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ConfirmLotCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmLotCommand.class);
    private final AuctionService auctionService;
    private final LotService lotService;

    public ConfirmLotCommand(LotService lotService, AuctionService auctionService) {
        this.lotService = lotService;
        this.auctionService = auctionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final User user = (User) context.getSession().getAttribute("user");
        Optional<Lot> lotOptional;
        try {
            final int lotId = Integer.parseInt(context.getParameter("lotId"));
            lotOptional = lotService.findById(lotId);
        } catch (NumberFormatException | RepositoryException e) {
            LOG.error("Error while finding lot by lotId", e);
            lotOptional = Optional.empty();
        }

        final String result = lotOptional.map(lot -> {
            lot.setVerifiedBy(user.getId());
            lot.setLotStatus(LotStatus.ACTIVE);
            final LocalDateTime now = LocalDateTime.now();
            lot.setStartAt(now);
            lot.setEndAt(now.plus(lot.getDurationInMinutes(), ChronoUnit.MINUTES));
            lotService.update(lot);
            auctionService.scheduleLotComplete(lot, lot.getDurationInMinutes(), TimeUnit.MINUTES);
            LOG.debug("Lot {} has been updated and scheduled", lot);
            return Json.createObjectBuilder()
                    .add("result", "ok").build().toString();
        }).orElseGet(() -> Json.createObjectBuilder()
                .add("result", "fail").build().toString());
        response.getWriter().write(result);
        return new CommandResult(ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(context));
    }
}
