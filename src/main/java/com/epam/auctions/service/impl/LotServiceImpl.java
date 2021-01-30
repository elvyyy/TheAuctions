package com.epam.auctions.service.impl;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.form.SearchForm;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.LotBidRepository;
import com.epam.auctions.repository.impl.LotRepository;
import com.epam.auctions.repository.specification.LikeSpecification;
import com.epam.auctions.repository.specification.IdSpecification;
import com.epam.auctions.repository.specification.LimitSpecification;
import com.epam.auctions.repository.specification.LotCreatedBySpecification;
import com.epam.auctions.repository.specification.LotIdSpecification;
import com.epam.auctions.repository.specification.LotStatusSpecification;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.repository.specification.SqlSpecificationUtils;
import com.epam.auctions.repository.specification.UsernameSpecification;
import com.epam.auctions.service.LotService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public enum LotServiceImpl implements LotService {
    INSTANCE;
    private Repository<Lot> lotRepository;
    private Repository<LotBid> lotBidRepository = new LotBidRepository();

    public static final int MIN_30 = 30;
    public static final int HOUR_1 = 60;
    public static final int HOUR_2 = 2 * 60;
    public static final int HOUR_24 = 24 * 60;
    public static final int HOUR_48 = 48 * 60;

    LotServiceImpl() {
        lotRepository = new LotRepository();
    }

    @Override
    public Lot create(Lot lot) {
        return lotRepository.insert(lot);
    }

    @Override
    public Optional<Lot> findById(int id) {
        return lotRepository.select(new IdSpecification(), id);
    }

    @Override
    public Collection<Lot> selectAll(SqlSpecification specification, Object... params) {
        return lotRepository.selectAll(specification, params);
    }

    @Override
    public Collection<Lot> selectAll(LotStatus lotStatus, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LotStatusSpecification(),
                new LimitSpecification());
        return lotRepository.selectAll(sql, lotStatus.getId(), lotsPerPage, offset);
    }

    @Override
    public Collection<Lot> selectBySearchForm(SearchForm form, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LikeSpecification(), new LimitSpecification());
        return lotRepository.selectAll(sql, "%" + form.getQuery() + "%",
                form.getLotStatusId(), lotsPerPage, offset);
    }

    @Override
    public Collection<Lot> selectByUserId(int userId, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LotCreatedBySpecification(),
                new LimitSpecification());
        return lotRepository.selectAll(sql, userId, lotsPerPage, offset);
    }

    @Override
    public long countLotsBySearchForm(SearchForm form) {
        return lotRepository.count(new LikeSpecification(), "%" + form.getQuery() + "%",
                form.getLotStatusId());
    }

    @Override
    public boolean update(Lot lot) {
        return lotRepository.update(lot);
    }

    @Override
    public boolean updateLotStatus(int lotId, LotStatus lotStatus, int verifiedBy) {
        return lotRepository.select(new IdSpecification(), lotId)
                .map(lot -> {
                    lot.setLotStatus(lotStatus);
                    lot.setVerifiedBy(verifiedBy);
                    return lotRepository.update(lot);
                }).orElse(false);
    }

    @Override
    public long countAllLots(LotStatus lotStatus) {
        return lotRepository.count(new LotStatusSpecification(), lotStatus.getId());
    }

    public Collection<LotBid> getLotBids(int lotId) {
        return lotBidRepository.selectAll(new LotIdSpecification(), lotId);
    }

    @Override
    public Collection<LotBid> getLotBids(String username) {
        return lotBidRepository.selectAll(new UsernameSpecification(), username);
    }

    @Override
    public Optional<LotBid> getMaxBid(int lotId) {
        return lotBidRepository.selectAll(new LotIdSpecification(), lotId)
                .stream()
                .max(Comparator.comparing(LotBid::getCurrentBid));
    }

    @Override
    public int getDuration(int option) {
        switch (option) {
            case 1:
                return MIN_30;
            case 2:
                return HOUR_1;
            case 3:
                return HOUR_2;
            case 4:
                return HOUR_24;
            case 5:
                return HOUR_48;
            default:
                throw new NoSuchElementException("No such choice exception #" + option);
        }
    }
}
