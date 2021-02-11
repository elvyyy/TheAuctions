package com.epam.auctions.db;

import java.sql.Connection;

/**
 * The interface Connection pool.
 */
public interface ConnectionPool {
    /**
     * Closes all connections.
     */
    void closeConnections();

    /**
     * Gets connection from the connection pool.
     *
     * @return the connection
     */
    Connection getConnection();

    /**
     * Releases connection to the connection pool.
     *
     * @param connection the connection
     * @return the boolean
     */
    boolean releaseConnection(Connection connection);
}
