package com.epam.auctions.service.impl;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.service.AuctionService;
import com.epam.auctions.service.LotService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum AuctionServiceImpl implements AuctionService {
    INSTANCE(LotServiceImpl.INSTANCE);

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

    private LotService lotService;

    AuctionServiceImpl(LotService lotService) {
        this.lotService = lotService;
    }

    public void scheduleLotComplete(Lot lot, long delay, TimeUnit unit) {
        executorService.schedule(() -> {
                lot.setLotStatus(LotStatus.COMPLETED);
                lotService.update(lot);
        }, delay, unit);
    }



}
