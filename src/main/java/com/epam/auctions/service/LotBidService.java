package com.epam.auctions.service;

import com.epam.auctions.entity.LotBid;

/**
 * The interface Lot bid service.
 */
public interface LotBidService {
    /**
     * Create bid lot bid.
     *
     * @param lotBid the lot bid
     * @return the lot bid
     */
    LotBid createBid(LotBid lotBid);
}
