package com.epam.auctions.repository;

import java.sql.SQLException;

public interface Repository<T> {
    T insert(T entity) throws SQLException;
    boolean update(T entity);
    boolean delete(T entity);
}
