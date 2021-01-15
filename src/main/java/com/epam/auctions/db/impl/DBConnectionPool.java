package com.epam.auctions.db.impl;

import com.epam.auctions.db.ConnectionPool;
import com.epam.auctions.exception.AllConnectionsBusy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public final class DBConnectionPool implements ConnectionPool {
    public static final DBConnectionPool INSTANCE = new DBConnectionPool();
    private String url;
    private String user;
    private String password;
    private BlockingQueue<Connection> availableConnections;

    private DBConnectionPool() {
        url = "jdbc:mysql://localhost:3306/jwd_auctions?characterEncoding=UTF-8";
        user = "root";
        password = "Ben432417";
        availableConnections = new ArrayBlockingQueue<>(20);
        // TODO: property file injection
        createConnections(20);
    }

    @SneakyThrows
    private void createConnections(final int numberOfConnections) {
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
     *
     * @return returns {@link Connection} if the connection pool has one,
     * otherwise throws {@link AllConnectionsBusy} exception
     */
    @Override
    public Connection getConnection() {
        log.info("Trying to get a connection");
        Connection connection;
        try {
            connection = availableConnections.take();
        } catch (InterruptedException e) {
            throw new AllConnectionsBusy("all connections are busy");
        }
        log.info("The connection has been retrieved");
        return connection;
    }


    /**
     * Releases connection to the pool which always has a free place
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
        // TODO: close all connections
        log.info("Closing available connections");
        availableConnections.forEach(c -> {
            try {
                ((ConnectionProxy) c).realClose();
            } catch (SQLException e) {
                log.error("", e);
            }
        });
    }
}
