package com.epam.auctions.service;

import com.epam.auctions.entity.Lot;

import java.util.concurrent.TimeUnit;

/**
 * The interface Auction service.
 */
public interface AuctionService {

    /**
     * Schedule lot complete.
     *
     * @param lot   the lot
     * @param delay the delay
     * @param unit  the unit
     */
    void scheduleLotComplete(Lot lot, long delay, TimeUnit unit);
}
