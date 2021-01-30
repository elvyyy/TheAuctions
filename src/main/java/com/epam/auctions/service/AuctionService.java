package com.epam.auctions.service;

import com.epam.auctions.entity.Lot;

import java.util.concurrent.TimeUnit;

public interface AuctionService {

    void scheduleLotComplete(Lot lot, long delay, TimeUnit unit);
}
