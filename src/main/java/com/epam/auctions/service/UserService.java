package com.epam.auctions.service;

import com.epam.auctions.entity.User;

public interface UserService {
    User register(User user);
    User findByUsername(String username);
}
