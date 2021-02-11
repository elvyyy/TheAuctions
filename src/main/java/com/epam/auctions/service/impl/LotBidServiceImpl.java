package com.epam.auctions.service.impl;

import com.epam.auctions.entity.LotBid;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.LotBidRepository;
import com.epam.auctions.service.LotBidService;

/**
 * The enum Lot bid service.
 */
public enum LotBidServiceImpl implements LotBidService {
    /**
     * Instance lot bid service.
     */
    INSTANCE;

    /**
     * Represent DAO object for {@link LotBid} class instances
     */
    private final Repository<LotBid> repository = new LotBidRepository();

    /**
     * Creates passed {@link LotBid} in data storage
     *
     * @param lotBid
     * @return inserted lotBid
     */
    public LotBid createBid(LotBid lotBid) {
        return repository.insert(lotBid);
    }
}
