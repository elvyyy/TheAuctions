package com.epam.auctions.service.impl;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.LotRepository;
import com.epam.auctions.repository.specification.IdSpecification;
import com.epam.auctions.repository.specification.LimitSpecification;
import com.epam.auctions.repository.specification.LotCreatedBySpecification;
import com.epam.auctions.repository.specification.LotStatusSpecification;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.repository.specification.SqlSpecificationUtils;
import com.epam.auctions.service.LotService;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public enum LotServiceImpl implements LotService {
    INSTANCE;
    private Repository<Lot> repository;

    LotServiceImpl() {
        repository = new LotRepository();
    }

    @Override
    public Lot create(Lot lot) {
        return repository.insert(lot);
    }

    @Override
    public Optional<Lot> findById(int id) {
        return repository.select(new IdSpecification(), id);
    }

    @Override
    public Collection<Lot> selectAll(SqlSpecification specification, Object... params) {
        return repository.selectAll(specification, params);
    }

    @Override
    public Collection<Lot> selectAll(LotStatus lotStatus, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LotStatusSpecification(),
                new LimitSpecification());
        return repository.selectAll(sql, lotStatus.getId(), lotsPerPage, offset);
    }

    @Override
    public Collection<Lot> selectByUserId(int userId, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LotCreatedBySpecification(),
                new LimitSpecification());
        return repository.selectAll(sql, userId, lotsPerPage, offset);
    }

    @Override
    public boolean updateLotStatus(int lotId, LotStatus lotStatus, int verifiedBy) {
        return repository.select(new IdSpecification(), lotId)
                .map(lot -> {
                    lot.setLotStatus(lotStatus);
                    lot.setVerifiedBy(verifiedBy);
                    return repository.update(lot);
                }).orElse(false);
    }

    @Override
    public long countAllLots(LotStatus lotStatus) {
        return repository.count(new LotStatusSpecification(), lotStatus.getId());
    }
}
