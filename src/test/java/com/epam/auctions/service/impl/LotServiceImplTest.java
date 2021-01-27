package com.epam.auctions.service.impl;

import com.epam.auctions.constant.Constants;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.repository.impl.LotRepository;
import com.epam.auctions.repository.specification.IdSpecification;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.service.LotService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@PrepareForTest(LotRepository.class)
class LotServiceImplTest {
    private LotService lotService = LotServiceImpl.INSTANCE;

    @Mock
    private LotRepository lotRepository;

    private Lot lot;

    @BeforeEach
    void init() {
        lot = new Lot();
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

    public LotServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(lotService, "repository", lotRepository);
    }

    @Test
    void selectAllTest() {
        final Collection<Lot> lots = lotService.selectAll(LotStatus.CANCELED,
                1, Constants.LOTS_PER_PAGE);

        Mockito.verify(lotRepository, Mockito.times(1))
                .selectAll(Mockito.notNull(SqlSpecification.class), Mockito.eq(LotStatus.CANCELED.getId()),
                        Mockito.eq(Constants.LOTS_PER_PAGE), Mockito.any());
    }

    @Test
    void findByIdTest() {
        int id = lot.getId();
        lot.setLotStatus(LotStatus.CANCELED);

        Mockito.when(lotRepository.select(Mockito.any(IdSpecification.class), Mockito.eq(id)))
                .thenReturn(Optional.of(lot));

        final Optional<Lot> lotOptional = lotService.findById(id);

        Mockito.verify(lotRepository, Mockito.times(1))
                .select(Mockito.any(IdSpecification.class), Mockito.eq(id));

        Assert.assertTrue(lotOptional.isPresent());
        Assert.assertEquals(lot.getId(), lotOptional.get().getId());
        Assert.assertEquals(lot.getLotStatus(), lotOptional.get().getLotStatus());
    }

    @Test
    void countAllLotsTest() {

    }
}