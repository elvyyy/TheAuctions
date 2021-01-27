package com.epam.auctions.repository;

import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JDBCUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCUtils.class);

    private JDBCUtils() {
    }

    private static ThreadLocal<Connection> connections = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection connection = connections.get();
        if (connection == null) {
            LOG.debug("No connection retrieved. Do you use @Transactional?");
            throw new RuntimeException("No connection retrieved. Do you use @Transactional?");
        }
        return connection;
    }

    public static void setCurrentConnection(Connection connection) {
        connections.set(connection);
    }

    public static void removeCurrentConnection() {
        connections.remove();
    }

    public static <T> T insert(Connection connection, String sql, ResultSetHandler<T> handler, Object... params) {
        try(PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            populateStatement(statement, params);
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to the database. Result=" + result);
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            return handler.handle(resultSet);
        } catch (SQLException e) {
            LOG.debug("", e);
            throw new RepositoryException("Some error occurred while inserting data");
        }
    }

    public static boolean delete(Connection connection, String sql, Object... params) {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            int result = statement.executeUpdate();
            if (result == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            LOG.debug("", e);
            throw new RepositoryException("Some error occurred while inserting data");
        }
    }

    public static <T> T select(Connection connection, String sql, ResultSetHandler<T> handler, Object... params) {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        } catch (SQLException e) {
            LOG.debug("", e);
            throw new RepositoryException("Some error occurred while selecting data");
        }
    }

    public static long count(Connection connection, String sql, Object... params) {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RepositoryException("Some error occurred while fetching data");
        }
    }

    private static void populateStatement(PreparedStatement statement, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        }
    }
}
