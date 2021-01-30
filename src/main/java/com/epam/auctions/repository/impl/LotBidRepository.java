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

public class LotBidRepository implements Repository<LotBid> {
    @Language("SQL")
    private static final String SQL_CREATE_BID =
            "INSERT INTO lot_history(lot_id, current_bid, created_by, created_at VALUE (?, ?, ?, ?)";

    @Language("SQL")
    public static final String SQL_SELECT_BID =
            "SELECT lot_history.lot_id, lot_history.current_bid, users.username, lot_history.created_at " +
                    "FROM lot_history " +
                    "INNER JOIN users " +
                    "ON lot_history.created_by=users.username";

    @Language("SQL")
    public static final String SQL_COUNT_BID = "SELECT COUNT(*) FROM lot_hystory";

    @Language("SQL")
    public static final String SQL_UPDATE_BID = "UPDATE lot_hystory SET lot_id=?, current_bid=?, created_by=?, created_at=?";

    @Language("SQL")
    public static final String SQL_DELETE_BID = "DELETE FROM lot_hystory WHERE lot_id=?";

    private static ResultSetHandler<?> insertHandler = rs -> 0;

    @Override
    @Transactional
    public LotBid insert(LotBid entity) {
        JDBCUtils.insert(JDBCUtils.getConnection(), SQL_CREATE_BID, insertHandler);
        return entity;
    }

    @Override
    @Transactional
    public boolean update(LotBid entity) {
        JDBCUtils.insert(JDBCUtils.getConnection(), SQL_UPDATE_BID, insertHandler,
                entity.getLotId(), entity.getCurrentBid(),
                entity.getCreatedBy(), entity.getCreatedAt());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(LotBid entity) {
        return JDBCUtils.delete(JDBCUtils.getConnection(), SQL_DELETE_BID, entity.getLotId());
    }

    @Override
    @Transactional
    public long count(SqlSpecification specification, Object... parameters) throws RepositoryException {
        return JDBCUtils.count(JDBCUtils.getConnection(), specification.getSql(SQL_COUNT_BID), parameters);
    }

    @Override
    @Transactional
    public Optional<LotBid> select(SqlSpecification specification, Object... parameters) {
       return JDBCUtils.select(JDBCUtils.getConnection(), specification.getSql(SQL_SELECT_BID),
               ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.LOT_BID_RESULT_SET_HANDLER),
               parameters);
    }

    @Override
    @Transactional
    public Collection<LotBid> selectAll(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(), specification.getSql(SQL_SELECT_BID),
                ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.LOT_BID_RESULT_SET_HANDLER),
                parameters);
    }
}
