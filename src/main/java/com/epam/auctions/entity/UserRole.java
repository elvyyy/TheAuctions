package com.epam.auctions.entity;

import com.epam.auctions.exception.UnknownRoleException;
import lombok.ToString;

import java.util.Arrays;

@ToString
public enum UserRole {
    USER(0),
    ADMIN(1);

    private int id;

    UserRole(final int id) {
        this.id = id;
    }

    public static UserRole fromId(int id) {
        return Arrays.stream(values())
                .filter(userRole -> userRole.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownRoleException("Such Role ID is not supported yet"));
    }

    public int getId() {
        return id;
    }
}
