package com.epam.auctions.service.impl;

import com.epam.auctions.constant.Constants;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.form.SearchForm;
import com.epam.auctions.repository.impl.LotBidRepository;
import com.epam.auctions.repository.impl.LotRepository;
import com.epam.auctions.repository.specification.IdSpecification;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.service.LotService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@PrepareForTest(LotRepository.class)
public class LotServiceImplTest {

    private LotService lotService = LotServiceImpl.INSTANCE;

    private LotRepository lotRepository;

    private LotBidRepository lotBidRepository;

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
        lotRepository = mock(LotRepository.class);
        lotBidRepository = mock(LotBidRepository.class);
        Whitebox.setInternalState(lotService, "lotRepository", lotRepository);
        Whitebox.setInternalState(lotService, "lotBidRepository", lotBidRepository);
    }


    @Test
    public void verifyThatLotServiceWillCallInsertOnLotRepositoryOneTime() {
        lotService.create(lot);
        verify(lotRepository, times(1))
                .insert(lot);
    }

    @Test
    void getLotWithIdThatEqualsToCertainId_When_FindByIdCalled_With_CertainId() {
        int id = lot.getId();
        lot.setLotStatus(LotStatus.CANCELED);

        Mockito.when(lotRepository.select(any(IdSpecification.class), eq(id)))
                .thenReturn(Optional.of(lot));

        final Optional<Lot> expected = lotService.findById(id);

        Mockito.verify(lotRepository, Mockito.times(1))
                .select(any(IdSpecification.class), eq(id));

        Assert.assertTrue(expected.isPresent());
        Assert.assertEquals(lot.getId(), expected.get().getId());
        Assert.assertEquals(lot.getLotStatus(), expected.get().getLotStatus());
    }

    @Test
    void verifyThatSelectAllWillBeCalledByLotRepositoryOneTime_When_LotServiceSelectAllCalled_With_LotStatus() {
        final Collection<Lot> lots = lotService.selectAll(LotStatus.CANCELED,
                1, Constants.LOTS_PER_PAGE);

        Mockito.verify(lotRepository, Mockito.times(1))
                .selectAll(any(SqlSpecification.class), eq(LotStatus.CANCELED.getId()),
                        eq(Constants.LOTS_PER_PAGE), any());
    }

    @Test
    public void verifyThatSelectAllWillBeCalledByLotRepositoryOneTime_When_LotServiceSelectAllCalled_With_IdSpecification() {
        SqlSpecification specification = new IdSpecification();
        Object[] params = new Object[0];

        lotService.selectAll(specification, params);

        verify(lotRepository, times(1))
                .selectAll(specification, params);
    }

    @Test
    public void verifyThatSelectAllWillBeCalledByLotRepository_When_SelectBySearchFormCalled_WithSearchForm() {
        String query = "query";
        String lotStatusId = "lotStatusId";
        int page = 0;
        int lotsPerPage = 0;
        int offset = (page - 1) * lotsPerPage;

        lotService.selectBySearchForm(new SearchForm(query, lotStatusId), page, lotsPerPage);

        verify(lotRepository, times(1))
                .selectAll(any(SqlSpecification.class),
                        eq("%" + query + "%"),
                        eq(lotStatusId),
                        eq(lotsPerPage),
                        eq(offset));
    }

    @Test
    public void verifyThatSelectAllWillBeCalledByLotRepositoryOneTime_When_SelectByUserIdCalled_With_UserId() {
        int userId = 1;
        int page = 0;
        int lotsPerPage = 0;
        int offset = (page - 1) * lotsPerPage;

        lotService.selectByUserId(userId, page, lotsPerPage);

        verify(lotRepository, times(1))
                .selectAll(any(SqlSpecification.class),
                        eq(userId),
                        eq(lotsPerPage),
                        eq(offset));
    }

    @Test
    public void verifyThatCountWillBeCalledByLotRepositoryOneTime_When_CountLotsBySearchFormCalled_With_SearchForm() {
        String query = "query";
        String lotStatusId = "lotStatusId";

        lotService.countLotsBySearchForm(new SearchForm(query, lotStatusId));

        verify(lotRepository, times(1))
                .count(any(SqlSpecification.class),
                        eq("%" + query + "%"),
                        eq(lotStatusId));
    }

    @Test
    public void verifyThatUpdateWillBeCalledByLotRepositoryOneTime_When_UpdateCalled_WithLot() {
        lotService.update(lot);

        verify(lotRepository, times(1))
                .update(eq(lot));
    }

    @Test
    public void verifyThatSelectAndUpdateWillBeCalledByLotRepositoryOneTime_When_UpdateLotStatusCalled_WithLotIdLotStatusAndVerifiedBy() {
        int lotId = 1;
        LotStatus lotStatus = LotStatus.ACTIVE;
        int verifiedBy = 0;

        doReturn(Optional.of(lot))
                .when(lotRepository)
                .select(any(SqlSpecification.class), eq(lotId));

        lotService.updateLotStatus(lotId, lotStatus, verifiedBy);

        verify(lotRepository, times(1))
                .select(any(SqlSpecification.class),
                        eq(lotId));

        verify(lotRepository, times(1))
                .update(eq(lot));
    }

    @Test
    public void verifyThatCountWillBeCalledByLotRepositoryOneTime_When_CountAllLotsCalled_With_LotStatus() {
        LotStatus lotStatus = LotStatus.ACTIVE;

        lotService.countAllLots(lotStatus);

        verify(lotRepository, times(1))
                .count(any(SqlSpecification.class),
                        eq(lotStatus.getId()));
    }

    @Test
    public void verifyThatSelectAllWillBeCalledByLotBidRepositoryOneTime_When_GetLotBidsCalled_With_LotId() {
        int lotId = 0;

        lotService.getLotBids(lotId);

        verify(lotBidRepository, times(1))
                .selectAll(any(SqlSpecification.class),
                        eq(lotId));
    }

    @Test
    public void verifyThatSelectAllWillBeCalledOneTime_When_GetLotBidsCalled_With_Username() {
        String username = "username";

        lotService.getLotBids(username);

        verify(lotBidRepository, times(1))
                .selectAll(any(SqlSpecification.class),
                        eq(username));
    }

    @Test
    public void verifyThatSelectAllWillBeCalledByLotBidRepositoryOneTime_When_GetMaxBidCalled_With_LotId() {
        int lotId = 0;

        LotBid lotBid = new LotBid(lotId, BigDecimal.TEN, "");

        doReturn(new ArrayList<>() {{
            add(lotBid);
        }})
                .when(lotBidRepository)
                .selectAll(any(SqlSpecification.class), eq(lotId));

        Optional<LotBid> maxBid = lotService.getMaxBid(lotId);

        verify(lotBidRepository, times(1))
                .selectAll(any(SqlSpecification.class), eq(lotId));

        assertTrue(maxBid.isPresent());

        assertEquals(lotBid, maxBid.get());
    }

    @Test
    public void getDuration_When_GetDurationCalled_With_LegalArgs() {
        int[] expectedValues = {30, 60, 2 * 60, 24 * 60, 48 * 60};

        for (int i = 0; i < expectedValues.length; ) {
            assertEquals(expectedValues[i], lotService.getDuration(++i));
        }
    }

    @Test
    public void getNoSuchElementException_When_CallGetDuration_With_IllegalParameter() {
        int option = 1000;

        assertThrows(NoSuchElementException.class,
                () -> lotService.getDuration(option),
                "No such choice exception #" + option);
    }

}