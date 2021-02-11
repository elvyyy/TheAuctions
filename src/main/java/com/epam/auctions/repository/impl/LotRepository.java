package com.epam.auctions.repository.impl;

import com.epam.auctions.annotation.Transactional;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.JDBCUtils;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.ResultSetHandler;
import com.epam.auctions.repository.ResultSetHandlerFactory;
import com.epam.auctions.repository.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

/**
 * The type Lot repository.
 */
public class LotRepository implements Repository<Lot> {
    /**
     * The constant SQL_COUNT_LOT.
     */
    @Language("SQL")
    private static final String SQL_COUNT_LOT = "SELECT COUNT(*) FROM lots";
    /**
     * The constant SQL_CREATE_LOT.
     */
    @Language("SQL")
    private static final String SQL_CREATE_LOT =
            "INSERT INTO lots(id, created_by, created_at, minimal_bid, lot_status_id, description, " +
                    "image_url, duration) " +
                    "VALUE (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
    /**
     * The constant SQL_DELETE_LOT.
     */
    @Language("SQL")
    private static final String SQL_DELETE_LOT = "DELETE FROM lots WHERE id=?";
    /**
     * The constant SQL_SELECT_LOT.
     */
    @Language("SQL")
    private static final String SQL_SELECT_LOT =
            "SELECT id, created_at, created_by, verified_by, minimal_bid, lot_status_id, description," +
                    " sold_to, image_url, end_at, start_at, duration FROM lots";
    /**
     * The constant SQL_UPDATE_LOT.
     */
    @Language("SQL")
    private static final String SQL_UPDATE_LOT =
            "UPDATE lots SET verified_by=?, minimal_bid=?," +
                    " lot_status_id=?, description=?, sold_to=?," +
                    " image_url=?, end_at=?, start_at=?, duration=? WHERE id=?";
    /**
     * The constant lotCreateSetHandler.
     */
    private static final ResultSetHandler<Integer> lotCreateSetHandler = rs -> {
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    };

    /**
     * Inserts entity into database
     *
     * @param entity the entity
     * @return inserted {@code Lot}
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public Lot insert(Lot entity) {
        final Integer id = JDBCUtils.insert(JDBCUtils.getConnection(), SQL_CREATE_LOT,
                lotCreateSetHandler,
                entity.getCreatedBy(),
                entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getMinimalBid().toString(),
                entity.getLotStatus().getId(),
                entity.getDescription(),
                entity.getPhotoPath(),
                entity.getDurationInMinutes());
        entity.setId(id);
        return entity;
    }

    /**
     * Updates entity in the database
     *
     * @param entity the entity
     * @return true
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public boolean update(Lot entity) {
        JDBCUtils.insert(JDBCUtils.getConnection(), SQL_UPDATE_LOT, lotCreateSetHandler,
                entity.getVerifiedBy(), entity.getMinimalBid(),
                entity.getLotStatus().getId(), entity.getDescription(),
                entity.getSoldTo(), entity.getPhotoPath(),
                entity.getEndAt() == null ? null : entity.getEndAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getStartAt() == null ? null : entity.getStartAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getDurationInMinutes(), entity.getId());
        return true;
    }

    /**
     * Deletes entity in the database
     *
     * @param entity the entity
     * @return true on success
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public boolean delete(Lot entity) {
        return JDBCUtils.delete(JDBCUtils.getConnection(), SQL_DELETE_LOT, entity.getId());
    }

    /**
     * Counts rows in the database by specification
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return
     * @throws RepositoryException
     */
    @Override
    @Transactional
    public long count(SqlSpecification specification, Object... parameters) throws RepositoryException {
        return JDBCUtils.count(JDBCUtils.getConnection(), specification.getSql(SQL_COUNT_LOT), parameters);
    }

    /**
     * Selects {@code Lot} from the database
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return {@code Optional}
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public Optional<Lot> select(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(), specification.getSql(SQL_SELECT_LOT),
                ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.LOT_RESULT_SET_HANDLER),
                parameters);
    }

    /**
     * Selects all rows by specification
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return {@code Collection}
     * @throws {@code RepositoryException} if cannot perform operation
     */
    @Override
    @Transactional
    public Collection<Lot> selectAll(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(), specification.getSql(SQL_SELECT_LOT),
                ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.LOT_RESULT_SET_HANDLER),
                parameters);
    }
}
