package com.epam.auctions.service.impl;

import com.epam.auctions.entity.User;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.repository.specification.EmailSpecification;
import com.epam.auctions.repository.specification.UsernameSpecification;
import com.epam.auctions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private Repository<User> repository;

    UserServiceImpl() {
        repository = new UserRepository();
    }

    UserServiceImpl(Repository<User> repository) {
        this.repository = repository;
    }

    public User register(User user) {
        return repository.insert(user);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return repository.select(new UsernameSpecification(), username);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return repository.select(new EmailSpecification(), email);
    }

    @Override
    public boolean update(User user) {
        return repository.update(user);
    }
}
