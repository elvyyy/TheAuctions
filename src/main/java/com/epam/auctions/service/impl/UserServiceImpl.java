package com.epam.auctions.service.impl;

import com.epam.auctions.db.ConnectionPool;
import com.epam.auctions.db.impl.DBConnectionPool;
import com.epam.auctions.entity.User;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.ResultSetHandler;
import com.epam.auctions.repository.ResultSetHandlerFactory;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.service.UserService;
import com.epam.auctions.util.JDBCUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public enum UserServiceImpl implements UserService {
    INSTANCE;

    Repository<User> repository = new UserRepository();

    public User register(User user) {
        try {
            return repository.insert(user);
        } catch (SQLException e) {
            log.error("Cannot create a user {}", user, e);
        }
        return null;
    }

    @Override
    public User getUser(final String username) {
        return null;
    }
}
