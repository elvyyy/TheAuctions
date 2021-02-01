package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.controller.LotController;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.BidNotAllowedException;
import com.epam.auctions.service.LotBidService;
import com.epam.auctions.service.LotService;
import com.epam.auctions.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class MakeBidCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(MakeBidCommand.class);
    private LotService lotService;
    private LotBidService lotBidService;

    public MakeBidCommand(LotService lotService, LotBidService lotBidService) {
        this.lotService = lotService;
        this.lotBidService = lotBidService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final User user = (User) context.getSession().getAttribute("user");
        try {
            final int lotId = Integer.parseInt(context.getParameter("l"));
            final int bidChoice = Integer.parseInt(context.getParameter("bid"));
            lotService.findById(lotId)
                    .ifPresent(lot -> {
                        final LotBid newBid = createNewBid(lot, bidChoice, user);
                        lotBidService.createBid(newBid);
                        LotController.sendMessage(lot.getId(), newBid);
                    });
            return WebUtils.responseOk(context, response);
        } catch (RuntimeException e) {
            LOG.debug("cannot make bid", e);
            return WebUtils.responseFail(context, response);
        }
    }

    private LotBid createNewBid(Lot lot, int choice, User user) {
        if (lot.getLotStatus() == LotStatus.ACTIVE) {
            return lotService
                    .getMaxBid(lot.getId())
                    .map(maxBid -> {
                        BigDecimal scaleFactor = getScaleFactor(choice);
                        BigDecimal result = maxBid.getCurrentBid().multiply(scaleFactor);
                        return new LotBid(lot.getId(), result, user.getUsername());
                    }).orElseGet(() -> {
                        BigDecimal scaleFactor = getScaleFactor(choice);
                        BigDecimal result = lot.getMinimalBid().multiply(scaleFactor);
                        return new LotBid(lot.getId(), result, user.getUsername());
                    });
        }
        throw new BidNotAllowedException("Current status is not ACTIVE");
    }

    public BigDecimal getScaleFactor(int choice) {
        switch (choice) {
            case 1:
                return new BigDecimal("1.1");
            case 2:
                return new BigDecimal("1.2");
            case 3:
                return new BigDecimal("1.3");
            case 4:
                return new BigDecimal("1.4");
            default:
                throw new UnsupportedOperationException("No such choice " + choice);
        }
    }
}
