package com.epam.auctions.service;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.repository.specification.SqlSpecification;

import java.util.Collection;
import java.util.Optional;

public interface LotService {
    Lot create(Lot lot);
    Optional<Lot> findById(int id);
    Collection<Lot> selectAll(SqlSpecification specification, Object... params);
    Collection<Lot> selectAll(LotStatus lotStatus, int page, int lotsPerPage);
    Collection<Lot> selectByUserId(int userId, int page, int lotsPerPage);
    boolean updateLotStatus(int lotId, LotStatus lotStatus, int verifiedBy);
    long countAllLots(LotStatus lotStatus);
}
