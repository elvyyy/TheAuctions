package com.epam.auctions.service;

import com.epam.auctions.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    boolean update(User user);
}
