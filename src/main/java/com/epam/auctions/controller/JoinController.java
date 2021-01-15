package com.epam.auctions.controller;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandFactory;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Page;
import com.epam.auctions.util.WebUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/join", name = "JoinController")
public class JoinController extends HttpServlet {
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Command command = CommandFactory.INSTANCE.getCommand(req);
        CommandResult result = command.execute(req, resp);
        WebUtils.processRouteRequest(result, req, resp, Page.SIGNUP_PAGE);
    }
}
