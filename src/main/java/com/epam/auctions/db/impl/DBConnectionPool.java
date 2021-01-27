package com.epam.auctions.db.impl;

import com.epam.auctions.db.ConnectionPool;
import com.epam.auctions.exception.AllConnectionsBusy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class DBConnectionPool implements ConnectionPool {
    private static final Logger log = LoggerFactory.getLogger(DBConnectionPool.class);

    public static final DBConnectionPool INSTANCE = new DBConnectionPool();
    private String url;
    private String user;
    private String password;
    private BlockingQueue<Connection> availableConnections;

    private DBConnectionPool() {
        availableConnections = new ArrayBlockingQueue<>(20);
        // TODO: property file injection
        try {
            createConnections(20);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createConnections(final int numberOfConnections) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        for (int i = 0; i < numberOfConnections; i++) {
            log.info("Creating connection #{}", i);
            try {
                ConnectionProxy connection = new ConnectionProxy(
                        DriverManager.getConnection(url, user, password)
                );
                availableConnections.add(connection);
            } catch (SQLException e) {
                log.error("Cannot create the connection #{i}", i, e);
            }
        }
    }

    /**
     * @return returns {@link Connection} if connection pool has one,
     * otherwise waiting until {@link AllConnectionsBusy} exception
     */
    @Override
    public Connection getConnection() {
        log.info("Trying to get a connection");
        Connection connection;
        try {
            connection = availableConnections.take();
        } catch (InterruptedException e) {
            throw new AllConnectionsBusy("Waiting for an available connection took so long.");
        }
        log.info("The connection has been retrieved");
        return connection;
    }

    /**
     * Releases connection to the pool which always has a free place
     *
     * @param connection
     * @return true
     */
    @Override
    public boolean releaseConnection(final Connection connection) {
        Objects.nonNull(connection);
        log.info("Trying to release a connection");
        availableConnections.add(connection);
        log.info("The connection has been released");
        return true;
    }

    @Override
    public void closeConnections() {
        log.info("Closing available connections");
        for (int i = 0; i < availableConnections.size(); i++) {
            try {
                log.debug("Closing connection #{}", i);
                final Connection connection = availableConnections.take();
                ((ConnectionProxy) connection).realClose();
            } catch (SQLException | InterruptedException e) {
                log.error("Some error occurred while closing connection #{}", i, e);
            }
        }
    }
}
