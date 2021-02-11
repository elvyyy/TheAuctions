package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.constant.Route;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.service.LotService;
import com.epam.auctions.validator.Validator;
import com.epam.auctions.validator.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateLotCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(CreateLotCommand.class);
    private final LotService lotService;

    public CreateLotCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final Validator<Lot> validator = ValidatorFactory.getValidator(Lot.class);
        final Lot lot = validator.validate(context);
        try {
            final Lot createdLot = lotService.create(lot);
            LOG.info("Created lot {}", createdLot);
            return new CommandResult(ResponseType.REDIRECT, Route.FEED_PAGE.getRoute());
        } catch (RepositoryException e) {
            LOG.error("Something went wrong while creating a new lot", e);
            Files.delete(Paths.get(context.getServletContext().getRealPath("/") + lot.getPhotoPath()));
        }
        return new CommandResult(ResponseType.REDIRECT, Route.CREATE_LOT_PAGE.getRoute());
    }
}
