package com.epam.auctions.service;

import com.epam.auctions.entity.User;

import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<User> findById(int id);

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findByUsername(String username);

    /**
     * Is email exists boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isEmailExists(String email);

    /**
     * Is username exists boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean isUsernameExists(String username);

    /**
     * Register user.
     *
     * @param user the user
     * @return the user
     */
    User register(User user);

    /**
     * Update boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean update(User user);
}

