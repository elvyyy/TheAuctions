package com.epam.auctions.repository;

import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.exception.TransactionalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Jdbc utils.
 */
public final class JDBCUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCUtils.class);
    /**
     * Used by aspects to put a connection in.
     */
    private static final ThreadLocal<Connection> connections = new ThreadLocal<>();

    private JDBCUtils() {
    }

    /**
     * Executes count query.
     *
     * @param connection the connection
     * @param sql        the sql
     * @param params     the params
     * @return the count
     * @throws {@link RepositoryException} if cannot execute query
     */
    public static long count(Connection connection, String sql, Object... params) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RepositoryException("Some error occurred while fetching data");
        }
    }

    /**
     * Executes delete query.
     *
     * @param connection the connection
     * @param sql        the sql
     * @param params     the params
     * @return the boolean
     * @throws {@link RepositoryException} if cannot execute query
     */
    public static boolean delete(Connection connection, String sql, Object... params) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            int result = statement.executeUpdate();
            return result != 0;
        } catch (SQLException e) {
            LOG.debug("", e);
            throw new RepositoryException("Some error occurred while inserting data");
        }
    }

    /**
     * Gets connection from {@code connections}.
     *
     * @return the connection
     * @throws {@link TransactionalException} if
     *                annotation {@link com.epam.auctions.annotation.Transactional} is not used
     */
    public static Connection getConnection() {
        Connection connection = connections.get();
        if (connection == null) {
            LOG.debug("No connection retrieved. Do you use @Transactional?");
            throw new TransactionalException("No connection retrieved. Do you use @Transactional?");
        }
        return connection;
    }

    /**
     * Executes insert query.
     *
     * @param <T>        the type parameter
     * @param connection the connection
     * @param sql        the sql
     * @param handler    the handler
     * @param params     the params
     * @return the t
     * @throws {@link RepositoryException} if cannot execute query
     */
    public static <T> T insert(Connection connection, String sql, ResultSetHandler<T> handler, Object... params) {
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

    /**
     * Populates statement with specified parameters
     *
     * @param statement
     * @param params
     * @throws {@link SQLException} if parameterIndex does not correspond to a parameter marker in the SQL statement;
     *                if a database access error occurs; this method is called on a closed PreparedStatement or the type of
     *                the given object is ambiguous
     */
    private static void populateStatement(PreparedStatement statement, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * Remove current connection.
     */
    public static void removeCurrentConnection() {
        connections.remove();
    }

    /**
     * Executes select query.
     *
     * @param <T>        the type parameter
     * @param connection the connection
     * @param sql        the sql
     * @param handler    the handler
     * @param params     the params
     * @return the t
     * @throws {@link RepositoryException} if cannot execute query
     */
    public static <T> T select(Connection connection, String sql, ResultSetHandler<T> handler, Object... params) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        } catch (SQLException e) {
            LOG.debug("", e);
            throw new RepositoryException("Some error occurred while selecting data");
        }
    }

    /**
     * Sets current connection.
     *
     * @param connection the connection
     */
    public static void setCurrentConnection(Connection connection) {
        connections.set(connection);
    }
}
