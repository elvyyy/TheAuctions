package com.epam.auctions.service.impl;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.service.AuctionService;
import com.epam.auctions.service.LotService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The enum Auction service.
 */
public enum AuctionServiceImpl implements AuctionService {
    /**
     * Instance auction service.
     */
    INSTANCE(LotServiceImpl.INSTANCE);

    private final ScheduledExecutorService executorService =
            Executors.newScheduledThreadPool(3);

    /**
     * Lot service interface
     */
    private final LotService lotService;

    AuctionServiceImpl(LotService lotService) {
        this.lotService = lotService;
    }

    /**
     * Schedules lot complete.
     *
     * @param lot   the lot
     * @param delay the delay
     * @param unit  the unit
     * @throws {@code com.epam.auctions.exception.LotNotCompletedException} if the lot cannot
     *                be completed.
     */
    public void scheduleLotComplete(Lot lot, long delay, TimeUnit unit) {
        executorService
                .schedule(() -> lotService.completeLot(lot), delay, unit);
    }


}
