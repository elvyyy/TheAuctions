package com.epam.auctions.util;

import com.epam.auctions.repository.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class JDBCUtils {
    private JDBCUtils() {
    }

    public static <T> T insert(Connection connection, String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            populateStatement(statement, params);
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to the database. Result=" + result);
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            return handler.handle(resultSet);
        }
    }

    public static <T> T select(Connection connection, String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        }
    }

    public static long count(Connection connection, String sql, Object... params) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            populateStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
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
