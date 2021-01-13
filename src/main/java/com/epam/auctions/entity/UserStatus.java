package com.epam.auctions.entity;

import lombok.ToString;

@ToString
public enum UserStatus {
    BANNED(0),
    REGISTERED(1);

    private int id;

    UserStatus(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
