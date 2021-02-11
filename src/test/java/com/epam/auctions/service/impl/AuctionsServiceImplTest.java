package com.epam.auctions.service.impl;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.service.AuctionService;
import com.epam.auctions.service.LotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@PrepareForTest(AuctionServiceImpl.class)
public class AuctionsServiceImplTest {

    private AuctionService auctionService = AuctionServiceImpl.INSTANCE;

    private ScheduledExecutorService executorService;
    private LotService lotService;
    private Lot lot;

    @BeforeEach
    public void init() {
        lot = spy(new Lot());
        lot.setId(5);
        lot.setLotStatus(LotStatus.CANCELED);
        lot.setVerifiedBy(4);
        lot.setDurationInMinutes(30);
        lot.setSoldTo(5);
        lot.setMinimalBid(BigDecimal.TEN);
        lot.setCreatedAt(LocalDateTime.now());
        lot.setStartAt(LocalDateTime.now());
        lot.setEndAt(LocalDateTime.now());
    }

    public AuctionsServiceImplTest() {
        lotService = mock(LotService.class);
        executorService = spy(Executors.newScheduledThreadPool(3));
        Whitebox.setInternalState(auctionService, "lotService", lotService);
        Whitebox.setInternalState(auctionService, "executorService", executorService);
    }

    @Test
    public void verifyThatLotWillBeCompleted_AfterCertainAmountOfTime() {
        auctionService.scheduleLotComplete(lot, 1, TimeUnit.NANOSECONDS);

        verify(executorService, times(1))
                .schedule(any(Runnable.class), anyLong(), any(TimeUnit.class));
        verify(lotService, times(1))
                .completeLot(eq(lot));
    }

}