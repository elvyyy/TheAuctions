package com.epam.auctions.repository.impl;

import com.epam.auctions.annotation.Transactional;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.JDBCUtils;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.ResultSetHandler;
import com.epam.auctions.repository.ResultSetHandlerFactory;
import com.epam.auctions.repository.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.util.Collection;
import java.util.Optional;

/**
 * The type Lot bid repository.
 */
public class LotBidRepository implements Repository<LotBid> {
    /**
     * The constant SQL_COUNT_BID.
     */
    @Language("SQL")
    public static final String SQL_COUNT_BID = "SELECT COUNT(*) FROM lot_hystory";
    /**
     * The constant SQL_DELETE_BID.
     */
    @Language("SQL")
    public static final String SQL_DELETE_BID = "DELETE FROM lot_hystory WHERE lot_id=?";
    /**
     * The constant SQL_SELECT_BID.
     */
    @Language("SQL")
    public static final String SQL_SELECT_BID =
            "SELECT lot_history.lot_id, lot_history.current_bid, users.username, lot_history.created_at " +
                    "FROM lot_history " +
                    "INNER JOIN users " +
                    "ON lot_history.created_by=users.username";
    /**
     * The constant SQL_UPDATE_BID.
     */
    @Language("SQL")
    public static final String SQL_UPDATE_BID = "UPDATE lot_hystory SET lot_id=?, current_bid=?, created_by=?, created_at=?";
    @Language("SQL")
    private static final String SQL_CREATE_BID =
            "INSERT INTO lot_history(lot_id, current_bid, created_by, created_at) VALUE (?, ?, ?, ?)";
    private static final ResultSetHandler<?> insertHandler = rs -> 0;

    /**
     * Inserts {@code entity} into database
     *
     * @param entity the entity
     * @return inserted {@code LotBid}
     */
    @Override
    @Transactional
    public LotBid insert(LotBid entity) {
        JDBCUtils.insert(JDBCUtils.getConnection(), SQL_CREATE_BID, insertHandler,
                entity.getLotId(), entity.getCurrentBid(),
                entity.getCreatedBy(), entity.getCreatedAt());
        return entity;
    }

    /**
     * Updated the entity in the database
     *
     * @param entity the entity
     * @return true on success
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public boolean update(LotBid entity) {
        JDBCUtils.insert(JDBCUtils.getConnection(), SQL_UPDATE_BID, insertHandler,
                entity.getLotId(), entity.getCurrentBid(),
                entity.getCreatedBy(), entity.getCreatedAt());
        return true;
    }

    /**
     * Deletes entity in the database
     *
     * @param entity the entity
     * @return true
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public boolean delete(LotBid entity) {
        return JDBCUtils.delete(JDBCUtils.getConnection(), SQL_DELETE_BID, entity.getLotId());
    }

    /**
     * Counts items by the specification
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public long count(SqlSpecification specification, Object... parameters) throws RepositoryException {
        return JDBCUtils.count(JDBCUtils.getConnection(), specification.getSql(SQL_COUNT_BID), parameters);
    }

    /**
     * Selects {@code LotBid} from the database by the specification
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return {@LotBid}
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public Optional<LotBid> select(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(), specification.getSql(SQL_SELECT_BID),
                ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.LOT_BID_RESULT_SET_HANDLER),
                parameters);
    }

    /**
     * Selects all the elements by the specification
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return collection of {@code LotBid}
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public Collection<LotBid> selectAll(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(), specification.getSql(SQL_SELECT_BID),
                ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.LOT_BID_RESULT_SET_HANDLER),
                parameters);
    }
}
