package com.epam.auctions.entity;

import com.epam.auctions.exception.UnknownRoleException;
import lombok.ToString;

import java.util.Arrays;

@ToString
public enum UserStatus {
    BANNED(0),
    REGISTERED(1);

    private int id;

    UserStatus(final int id) {
        this.id = id;
    }

    public static UserStatus fromId(int id) {
        return Arrays.stream(values())
                .filter(userStatus -> userStatus.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownRoleException("Such Status ID is not supported yet"));
    }

    public int getId() {
        return id;
    }
}
