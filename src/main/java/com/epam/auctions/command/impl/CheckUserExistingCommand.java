package com.epam.auctions.command.impl;

import com.epam.auctions.command.Command;
import com.epam.auctions.command.CommandResult;
import com.epam.auctions.constant.Constants;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.specification.EmailSpecification;
import com.epam.auctions.repository.specification.UsernameSpecification;
import com.epam.auctions.util.WebUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class CheckUserExistingCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(CheckUserExistingCommand.class);

    private Repository<User> repository;

    public CheckUserExistingCommand(Repository<User> repository) {
        this.repository = repository;
    }

    @Override
    public CommandResult execute(HttpServletRequest context, HttpServletResponse response) throws IOException {
        Optional<String> expectedEmail = Optional.ofNullable(context.getParameter(Constants.EMAIL.value()));
        Optional<String> expectedUsername = Optional.ofNullable(context.getParameter(Constants.USERNAME.value()));
        ResponseInfo responseInfo = new ResponseInfo();

        expectedUsername.ifPresent(username ->
        {
            try {
                long count = repository.count(new UsernameSpecification(), username);
                responseInfo.setNumberOfUsername(count);
            } catch (RepositoryException e) {
                LOG.error("", e);
            }
        });
        expectedEmail.ifPresent(email ->
        {
            try {
                long count = repository.count(new EmailSpecification(), email);
                responseInfo.setNumberOfEmail(count);
            } catch (RepositoryException e) {
                LOG.error("", e);
            }
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
