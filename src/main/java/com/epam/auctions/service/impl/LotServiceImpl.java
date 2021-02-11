package com.epam.auctions.service.impl;

import com.epam.auctions.controller.LotController;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.BidNotAllowedException;
import com.epam.auctions.exception.LotNotCompletedException;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.form.SearchForm;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.impl.LotBidRepository;
import com.epam.auctions.repository.impl.LotRepository;
import com.epam.auctions.repository.specification.IdSpecification;
import com.epam.auctions.repository.specification.LikeSpecification;
import com.epam.auctions.repository.specification.LimitSpecification;
import com.epam.auctions.repository.specification.LotCreatedBySpecification;
import com.epam.auctions.repository.specification.LotIdSpecification;
import com.epam.auctions.repository.specification.LotStatusSpecification;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.repository.specification.SqlSpecificationUtils;
import com.epam.auctions.repository.specification.UsernameSpecification;
import com.epam.auctions.service.LotBidService;
import com.epam.auctions.service.LotService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The enum Lot service.
 */
public enum LotServiceImpl implements LotService {
    /**
     * Instance lot service.
     */
    INSTANCE;
    /**
     * The constant HOUR_1.
     */
    public static final int HOUR_1 = 60;
    /**
     * The constant HOUR_2.
     */
    public static final int HOUR_2 = 2 * 60;
    /**
     * The constant HOUR_24.
     */
    public static final int HOUR_24 = 24 * 60;
    /**
     * The constant HOUR_48.
     */
    public static final int HOUR_48 = 48 * 60;
    /**
     * The constant MIN_30.
     */
    public static final int MIN_30 = 30;
    /**
     * @guards {@code createNewBid}
     */
    private final ReentrantLock bidTransactionLock = new ReentrantLock();
    /**
     * Lot Bid repository
     */
    private final Repository<LotBid> lotBidRepository = new LotBidRepository();
    /**
     * Lot bid service
     */
    private final LotBidService lotBidService = LotBidServiceImpl.INSTANCE;
    /**
     * Lot Repository
     */
    private final Repository<Lot> lotRepository;

    LotServiceImpl() {
        lotRepository = new LotRepository();
    }

    /**
     * Creates new lot
     *
     * @param lot the lot
     * @return {@code Lot}
     * @throws {@code RepositoryException} if cannot perform action
     */
    @Override
    public Lot create(Lot lot) {
        return lotRepository.insert(lot);
    }

    /**
     * Finds lot by id
     *
     * @param id the id
     * @return {@code Optional}
     * @throws {@code RepositoryException} if cannot perform action
     */
    @Override
    public Optional<Lot> findById(int id) {
        return lotRepository.select(new IdSpecification(), id);
    }

    /**
     * Selects all {@code Lot}s
     *
     * @param specification the specification
     * @param params        the params
     * @return {@code Collection}
     * @throws {@code RepositoryException} if cannot perform action
     */
    @Override
    public Collection<Lot> selectAll(SqlSpecification specification, Object... params) {
        return lotRepository.selectAll(specification, params);
    }

    /**
     * Selects all lots by {@code lotStatus}
     *
     * @param lotStatus   the lot status
     * @param page        the page
     * @param lotsPerPage the lots per page
     * @return {@code Collection}
     * @throws {@code RepositoryException} if cannot perform action
     */
    @Override
    public Collection<Lot> selectAll(LotStatus lotStatus, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LotStatusSpecification(),
                new LimitSpecification());
        return lotRepository.selectAll(sql, lotStatus.getId(), lotsPerPage, offset);
    }

    /**
     * Selects rows by Seatch form
     *
     * @param form        the form
     * @param page        the page
     * @param lotsPerPage the lots per page
     * @return {@code Collection}
     */
    @Override
    public Collection<Lot> selectBySearchForm(SearchForm form, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LikeSpecification(), new LimitSpecification());
        return lotRepository.selectAll(sql, "%" + form.getQuery() + "%",
                form.getLotStatusId(), lotsPerPage, offset);
    }

    /**
     * Selects {@code Lot} by {@code userId}
     *
     * @param userId      the user id
     * @param page        the page
     * @param lotsPerPage the lots per page
     * @return {@code Collection}
     */
    @Override
    public Collection<Lot> selectByUserId(int userId, int page, int lotsPerPage) {
        int offset = (page - 1) * lotsPerPage;
        final SqlSpecification sql = SqlSpecificationUtils.concat(new LotCreatedBySpecification(),
                new LimitSpecification());
        return lotRepository.selectAll(sql, userId, lotsPerPage, offset);
    }

    /**
     * Counts lots by serach form
     *
     * @param form the form
     * @return count
     */
    @Override
    public long countLotsBySearchForm(SearchForm form) {
        return lotRepository.count(new LikeSpecification(), "%" + form.getQuery() + "%",
                form.getLotStatusId());
    }

    /**
     * Updated lot
     *
     * @param lot the lot
     * @return true on success
     */
    @Override
    public boolean update(Lot lot) {
        return lotRepository.update(lot);
    }

    /**
     * Updates lot status by lot id
     *
     * @param lotId      the lot id
     * @param lotStatus  the lot status
     * @param verifiedBy the verified by
     * @return true on success
     */
    @Override
    public boolean updateLotStatus(int lotId, LotStatus lotStatus, int verifiedBy) {
        return lotRepository.select(new IdSpecification(), lotId)
                .map(lot -> {
                    lot.setLotStatus(lotStatus);
                    lot.setVerifiedBy(verifiedBy);
                    return lotRepository.update(lot);
                }).orElse(false);
    }

    /**
     * Count all lots by lot status
     *
     * @param lotStatus the lot status
     * @return count
     */
    @Override
    public long countAllLots(LotStatus lotStatus) {
        return lotRepository.count(new LotStatusSpecification(), lotStatus.getId());
    }

    /**
     * Gets duration by option
     *
     * @param option the option
     * @return duration
     */
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

    /**
     * Gets lot bids by lot id
     *
     * @param lotId the lot id
     * @return collections of lot bids
     */
    @Override
    public Collection<LotBid> getLotBids(int lotId) {
        return lotBidRepository.selectAll(new LotIdSpecification(), lotId);
    }

    /**
     * Gets lot bids by username
     *
     * @param username the username
     * @return collections of lot bids
     */
    @Override
    public Collection<LotBid> getLotBids(String username) {
        return lotBidRepository.selectAll(new UsernameSpecification(), username);
    }

    /**
     * Gets max bid of the lot
     *
     * @param lotId the lot id
     * @return {@code Optional}
     */
    @Override
    public Optional<LotBid> getMaxBid(int lotId) {
        return lotBidRepository.selectAll(new LotIdSpecification(), lotId)
                .stream()
                .max(Comparator.comparing(LotBid::getCurrentBid));
    }

    /**
     * Creates new bid.
     *
     * @param lotId       the lot id
     * @param scaleFactor the scale factor
     * @param user        the user
     * @throws {@code BidNotAllowedException} if cannot perform insert action
     *                or {@link Lot} is already completed
     */
    public void createNewBid(int lotId, BigDecimal scaleFactor, User user) {
        bidTransactionLock.lock();
        try {
            this.findById(lotId)
                    .ifPresent(lot -> {
                        final LotBid newBid = produceNewBid(lot, scaleFactor, user);
                        lotBidService.createBid(newBid);
                        LotController.sendMessage(lot.getId(), newBid);
                    });
            bidTransactionLock.unlock();
        } catch (RepositoryException | BidNotAllowedException e) {
            bidTransactionLock.unlock();
            throw new BidNotAllowedException("Cannot create the bid", e);
        }
    }

    /**
     * Produces not {@link LotBid} scaling previous result
     *
     * @param lot
     * @param scaleFactor
     * @param user
     * @return {@link LotBid}
     * @throws {@link BidNotAllowedException} if {@code LotStatus} is not {@code LotStatus.ACTIVE}
     */
    private LotBid produceNewBid(Lot lot, BigDecimal scaleFactor, User user) {
        if (lot.getLotStatus() == LotStatus.ACTIVE) {
            return getMaxBid(lot.getId())
                    .map(maxBid -> {
                        BigDecimal result = maxBid.getCurrentBid().multiply(scaleFactor);
                        return new LotBid(lot.getId(), result, user.getUsername());
                    }).orElseGet(() -> {
                        BigDecimal result = lot.getMinimalBid().multiply(scaleFactor);
                        return new LotBid(lot.getId(), result, user.getUsername());
                    });
        }
        throw new BidNotAllowedException("Current status is not ACTIVE");
    }

    /**
     * Completes lot
     *
     * @param lot the lot
     * @throws {@code LotNotCompletedException} if cannot complete the lot
     */
    @Override
    public void completeLot(Lot lot) {
        lot.setLotStatus(LotStatus.COMPLETED);
        bidTransactionLock.lock();
        try {
            this.update(lot);
        } catch (RepositoryException e) {
            bidTransactionLock.unlock();
            throw new LotNotCompletedException("Cannot complete lot", e);
        }

    }
}
