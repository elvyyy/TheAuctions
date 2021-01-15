package com.epam.auctions.repository;

import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.specification.SqlSpecification;

import java.sql.SQLException;

public interface Repository<T> {
    T insert(T entity) throws SQLException;
    boolean update(T entity);
    boolean delete(T entity);
    long count(SqlSpecification specification) throws RepositoryException;
}
