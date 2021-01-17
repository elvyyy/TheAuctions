package com.epam.auctions.service.impl;

import com.epam.auctions.entity.User;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.repository.specification.UsernameSpecification;
import com.epam.auctions.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Optional;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public Optional<User> findByUsername(final String username) {
        return repository.select(new UsernameSpecification(username));
    }
}
