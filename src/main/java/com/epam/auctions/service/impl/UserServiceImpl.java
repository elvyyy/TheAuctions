package com.epam.auctions.service.impl;

import com.epam.auctions.entity.User;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.repository.specification.EmailSpecification;
import com.epam.auctions.repository.specification.IdSpecification;
import com.epam.auctions.repository.specification.UsernameSpecification;
import com.epam.auctions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The enum User service.
 */
public enum UserServiceImpl implements UserService {
    /**
     * Instance user service.
     */
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * User repository
     */
    private final Repository<User> repository;

    UserServiceImpl() {
        repository = new UserRepository();
    }

    UserServiceImpl(Repository<User> repository) {
        this.repository = repository;
    }

    /**
     * Registers new user
     *
     * @param user the user
     * @return registered user
     */
    @Override
    public User register(User user) {
        return repository.insert(user);
    }

    /**
     * Finds user by username
     *
     * @param username the username
     * @return {@code Optional}
     */
    @Override
    public Optional<User> findByUsername(final String username) {
        return repository.select(new UsernameSpecification(), username);
    }

    /**
     * Finds user by id
     *
     * @param id the id
     * @return {@code Optional}
     */
    @Override
    public Optional<User> findById(int id) {
        return repository.select(new IdSpecification(), id);
    }

    /**
     * Finds user by email
     *
     * @param email the email
     * @return {@code Optional}
     */
    @Override
    public Optional<User> findByEmail(final String email) {
        return repository.select(new EmailSpecification(), email);
    }

    /**
     * Checks whether user exists or not
     *
     * @param username the username
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean isUsernameExists(String username) {
        return repository
                .select(new UsernameSpecification(), username)
                .isPresent();
    }

    /**
     * Checks whether email exists or not
     *
     * @param email the email
     * @return true if email exists otherwise false
     */
    @Override
    public boolean isEmailExists(String email) {
        return repository
                .select(new EmailSpecification(), email)
                .isPresent();
    }

    /**
     * Updates user
     *
     * @param user the user
     * @return true on success
     */
    @Override
    public boolean update(User user) {
        return repository.update(user);
    }
}
