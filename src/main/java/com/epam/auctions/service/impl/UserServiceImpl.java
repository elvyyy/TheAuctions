package com.epam.auctions.service.impl;

import com.epam.auctions.entity.User;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

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
    public User findByUsername(final String username) {
        return null;
    }
}
