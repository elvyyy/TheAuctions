package com.epam.auctions.repository;

import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.specification.SqlSpecification;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {
    T insert(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    long count(SqlSpecification specification, Object... parameters) throws RepositoryException;

    Optional<T> select(SqlSpecification specification, Object... parameters);

    Collection<T> selectAll(SqlSpecification specification, Object... parameters);
}
