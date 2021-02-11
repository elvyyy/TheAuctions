package com.epam.auctions.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The functional interface Result set handler.
 *
 * @param <T> the type parameter
 */
@FunctionalInterface
public interface ResultSetHandler<T> {
    /**
     * Result set handler.
     *
     * @param resultSet the result set
     * @return the entity
     * @throws SQLException if cannot parse {@code resultSet}
     */
    T handle(ResultSet resultSet) throws SQLException;
}
