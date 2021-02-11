package com.epam.auctions.repository;

import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.specification.SqlSpecification;

import java.util.Collection;
import java.util.Optional;

/**
 * The interface Repository.
 *
 * @param <T> the type parameter
 */
public interface Repository<T> {
    /**
     * Counts table rows.
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return the long
     * @throws {@link RepositoryException} if cannot perform action
     */
    long count(SqlSpecification specification, Object... parameters) throws RepositoryException;

    /**
     * Deletes entity.
     *
     * @param entity the entity
     * @return the boolean
     * @throws {@link RepositoryException} if cannot perform action
     */
    boolean delete(T entity);

    /**
     * Inserts entity.
     *
     * @param entity the entity
     * @return the t
     * @throws {@link RepositoryException} if cannot perform action
     */
    T insert(T entity);

    /**
     * Selects rows with specification.
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return the optional
     * @throws {@link RepositoryException} if cannot perform action
     */
    Optional<T> select(SqlSpecification specification, Object... parameters);

    /**
     * Selects all rows by specification.
     *
     * @param specification the specification
     * @param parameters    the parameters
     * @return the collection
     * @throws {@link RepositoryException} if cannot perform action
     */
    Collection<T> selectAll(SqlSpecification specification, Object... parameters);

    /**
     * Updates entity.
     *
     * @param entity the entity
     * @return the boolean
     * @throws {@link RepositoryException} if cannot perform action
     */
    boolean update(T entity);
}
