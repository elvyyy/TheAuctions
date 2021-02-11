package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.command.CommandResult.ResponseType;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.service.LotService;
import com.epam.auctions.util.WebUtils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelLotCommand implements Command {
    private final LotService lotService;

    public CancelLotCommand(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException, ServletException {
        final int lotId = Integer.valueOf(context.getParameter("lotId"));
        final User user = (User) context.getSession().getAttribute("user");
        final boolean result = lotService.updateLotStatus(lotId, LotStatus.CANCELED, user.getId());
        final JsonObject jsonResponse = Json.createObjectBuilder()
                .add("result", result)
                .build();
        response.getWriter().write(jsonResponse.toString());
        response.setContentType("application/json");
        return new CommandResult(ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(context));
    }
}
