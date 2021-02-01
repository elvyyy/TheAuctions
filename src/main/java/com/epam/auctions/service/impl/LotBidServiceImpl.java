package com.epam.auctions.service.impl;

import com.epam.auctions.entity.LotBid;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.LotBidRepository;
import com.epam.auctions.service.LotBidService;

public enum LotBidServiceImpl implements LotBidService {
    INSTANCE;

    private Repository<LotBid> repository = new LotBidRepository();

    public LotBid createBid(LotBid lotBid) {
        return repository.insert(lotBid);
    }
}
