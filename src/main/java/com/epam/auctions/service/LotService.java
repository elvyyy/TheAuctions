package com.epam.auctions.service;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.form.SearchForm;
import com.epam.auctions.repository.specification.SqlSpecification;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

/**
 * The interface Lot service.
 */
public interface LotService {
    /**
     * Completes lot.
     *
     * @param lot the lot
     * @throws {@code com.epam.auctions.exception.LotNotCompletedException} if database
     *                exception occurs.
     */
    void completeLot(Lot lot);

    /**
     * Count all lots long.
     *
     * @param lotStatus the lot status
     * @return the long
     */
    long countAllLots(LotStatus lotStatus);

    /**
     * Count lots by search form long.
     *
     * @param form the form
     * @return the long
     */
    long countLotsBySearchForm(SearchForm form);

    /**
     * Create lot.
     *
     * @param lot the lot
     * @return the lot
     */
    Lot create(Lot lot);

    /**
     * Creates new bid.
     *
     * @param lotId       the lot id
     * @param scaleFactor the scale factor
     * @param user        the user
     * @throws {@link com.epam.auctions.exception.BidNotAllowedException} if cannot create the bid
     */
    void createNewBid(int lotId, BigDecimal scaleFactor, User user);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Lot> findById(int id);

    /**
     * Gets duration.
     *
     * @param option the option
     * @return the duration
     */
    int getDuration(int option);

    /**
     * Gets lot bids.
     *
     * @param lotId the lot id
     * @return the lot bids
     */
    Collection<LotBid> getLotBids(int lotId);

    /**
     * Gets lot bids.
     *
     * @param username the username
     * @return the lot bids
     */
    Collection<LotBid> getLotBids(String username);

    /**
     * Gets max bid.
     *
     * @param lotId the lot id
     * @return the max bid
     */
    Optional<LotBid> getMaxBid(int lotId);

    /**
     * Select all collection.
     *
     * @param specification the specification
     * @param params        the params
     * @return the collection
     */
    Collection<Lot> selectAll(SqlSpecification specification, Object... params);

    /**
     * Select all collection.
     *
     * @param lotStatus   the lot status
     * @param page        the page
     * @param lotsPerPage the lots per page
     * @return the collection
     */
    Collection<Lot> selectAll(LotStatus lotStatus, int page, int lotsPerPage);

    /**
     * Select by search form collection.
     *
     * @param form        the form
     * @param page        the page
     * @param lotsPerPage the lots per page
     * @return the collection
     */
    Collection<Lot> selectBySearchForm(SearchForm form, int page, int lotsPerPage);

    /**
     * Select by user id collection.
     *
     * @param userId      the user id
     * @param page        the page
     * @param lotsPerPage the lots per page
     * @return the collection
     */
    Collection<Lot> selectByUserId(int userId, int page, int lotsPerPage);

    /**
     * Update boolean.
     *
     * @param lot the lot
     * @return the boolean
     */
    boolean update(Lot lot);

    /**
     * Update lot status boolean.
     *
     * @param lotId      the lot id
     * @param lotStatus  the lot status
     * @param verifiedBy the verified by
     * @return the boolean
     */
    boolean updateLotStatus(int lotId, LotStatus lotStatus, int verifiedBy);
}
