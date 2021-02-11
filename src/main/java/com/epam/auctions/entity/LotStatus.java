package com.epam.auctions.entity;

import com.epam.auctions.exception.UnknownStatusException;

import java.util.Arrays;

public enum LotStatus {
    CREATED(1),
    VERIFIED(2),
    ACTIVE(3),
    COMPLETED(4),
    CANCELED(5);

    private final int id;

    LotStatus(final int id) {
        this.id = id;
    }

    public static LotStatus fromId(int id) {
        return Arrays.stream(values())
                .filter(lotStatus -> lotStatus.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownStatusException("Such Status ID is not supported yet"));
    }

    public int getId() {
        return id;
    }
}
