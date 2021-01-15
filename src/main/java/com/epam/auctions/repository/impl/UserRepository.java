package com.epam.auctions.repository.impl;

import com.epam.auctions.db.impl.DBConnectionPool;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.AllConnectionsBusy;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.ResultSetHandler;
import com.epam.auctions.repository.ResultSetHandlerFactory;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.util.JDBCUtils;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository implements Repository<User> {

    @Language("MySQL")
    private static final String SQL_CREATE_USER =
            "INSERT INTO users(id, username, email, password, first_name, middle_name," +
                    " last_name, user_role_id, user_status_id) " +
                    "VALUE (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Language("MySQL")
    private static final String SQL_COUNT_USER = "SELECT COUNT(*) FROM users";

    private static final ResultSetHandler<Optional<User>> userResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Integer> userIdSetHandler = rs -> {
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    };


    @Override
    public User insert(User entity) throws SQLException {
        try (Connection connection = DBConnectionPool.INSTANCE.getConnection()) {
            Integer id = JDBCUtils.insert(connection, SQL_CREATE_USER, userIdSetHandler,
                    entity.getUsername(), entity.getEmail(),
                    entity.getPassword(), entity.getFirstName(),
                    entity.getMiddleName(), entity.getLastName(),
                    entity.getUserRole().getId(), entity.getUserStatus().getId());
            entity.setId(id);
            return entity;
        }
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public long count(SqlSpecification specification) throws RepositoryException {
        try(Connection connection = DBConnectionPool.INSTANCE.getConnection()) {
            return JDBCUtils.count(connection,
                    specification.getSql(SQL_COUNT_USER), specification.getParameters());
        } catch (SQLException | AllConnectionsBusy e) {
            throw new RepositoryException("Cannot process count operation");
        }
    }
}
