package com.epam.auctions.repository.specification;

/**
 * The interface Sql specification.
 */
public interface SqlSpecification {
    /**
     * Get parameters object [ ].
     *
     * @return the object [ ]
     */
    Object[] getParameters();

    /**
     * Gets specification.
     *
     * @return the specification
     */
    String getSpecification();

    /**
     * Gets sql.
     *
     * @param baseSql the base sql
     * @return the sql
     */
    String getSql(String baseSql);
}
