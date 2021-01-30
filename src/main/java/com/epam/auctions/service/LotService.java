package com.epam.auctions.service;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.form.SearchForm;
import com.epam.auctions.repository.specification.SqlSpecification;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface LotService {
    Lot create(Lot lot);
    Optional<Lot> findById(int id);
    Collection<Lot> selectAll(SqlSpecification specification, Object... params);
    Collection<Lot> selectAll(LotStatus lotStatus, int page, int lotsPerPage);
    Collection<Lot> selectBySearchForm(SearchForm form, int page, int lotsPerPage);
    Collection<Lot> selectByUserId(int userId, int page, int lotsPerPage);
    long countLotsBySearchForm(SearchForm form);
    boolean update(Lot lot);
    boolean updateLotStatus(int lotId, LotStatus lotStatus, int verifiedBy);
    long countAllLots(LotStatus lotStatus);
    int getDuration(int option);
    Collection<LotBid> getLotBids(int lotId);
    Collection<LotBid> getLotBids(String username);
    Optional<LotBid> getMaxBid(int lotId);
}
