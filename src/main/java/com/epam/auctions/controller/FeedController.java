package com.epam.auctions.controller;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandFactory;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;
import com.epam.auctions.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FeedController", urlPatterns = "/feed")
@MultipartConfig
public class FeedController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = CommandFactory.INSTANCE.getCommand(req);
        CommandResult result = command.execute(req, resp);
        WebUtils.processRouteRequest(result, req, resp, Page.FEED_PAGE);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
