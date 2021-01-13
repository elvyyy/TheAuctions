package com.epam.auctions.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Slf4j
@WebServlet(name = "SelectLocaleServlet", urlPatterns = "/ajax/html/change/lang")
public class SelectLocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        if (lang != null) {
            String[] locale = lang.split("_");
            log.info("Данные с AJAX запроса: data - {}", lang);
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            resp.setLocale(new Locale(locale[0]));
            req.getSession().setAttribute("locale", locale[0]);
            resp.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=Strict");
            resp.setStatus(200);
        }
    }
}
