package com.epam.auctions.controller;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandFactory;
import com.epam.auctions.command.RequestContext;
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
//        RequestContext requestContext = new RequestContext.Builder()
//                .parseHttpServletRequest(req)
//                .build();
//
//        Command command = CommandFactory.INSTANCE.getCommand(requestContext);
//        command.execute(requestContext);

        req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestContext requestContext = new RequestContext.Builder()
                .parseHttpServletRequest(req)
                .build();

        Command command = CommandFactory.INSTANCE.getCommand(requestContext);
        command.execute(requestContext);
    }
}
