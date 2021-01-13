package com.epam.auctions.entity;

import lombok.ToString;

@ToString
public enum UserRole {
    USER(0),
    ADMIN(1);

    private int id;

    UserRole(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
