package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.service.UserService;
import com.epam.auctions.util.WebUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class CheckUserExistingCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(CheckUserExistingCommand.class);

    private final UserService userService;

    public CheckUserExistingCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException {
        Optional<String> expectedEmail = Optional.ofNullable(context.getParameter(Constants.EMAIL.value()));
        Optional<String> expectedUsername = Optional.ofNullable(context.getParameter(Constants.USERNAME.value()));
        ResponseInfo responseInfo = new ResponseInfo();


        expectedUsername.ifPresent(username -> {
            final boolean usernameExists = userService.isUsernameExists(username);
            responseInfo.setNumberOfUsername(usernameExists ? 1 : 0);
        });
        expectedEmail.ifPresent(email -> {
            boolean emailExists = userService.isEmailExists(email);
            responseInfo.setNumberOfEmail(emailExists ? 1 : 0);
        });

        fillOutWithJsonResponse(response, responseInfo);
        return new CommandResult(CommandResult.ResponseType.NO_ACTION, WebUtils.getCurrentRequestUrl(context));
    }

    private void fillOutWithJsonResponse(HttpServletResponse response, ResponseInfo responseInfo) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(responseInfo);
        out.print(jsonString);
        out.flush();
    }


    private class ResponseInfo {
        private long numberOfEmail;
        private long numberOfUsername;

        public long getNumberOfEmail() {
            return numberOfEmail;
        }

        public void setNumberOfEmail(long numberOfEmail) {
            this.numberOfEmail = numberOfEmail;
        }

        public long getNumberOfUsername() {
            return numberOfUsername;
        }

        public void setNumberOfUsername(long numberOfUsername) {
            this.numberOfUsername = numberOfUsername;
        }
    }
}
