package com.epam.auctions.repository.impl;

import com.epam.auctions.annotation.Transactional;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.JDBCUtils;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.ResultSetHandler;
import com.epam.auctions.repository.ResultSetHandlerFactory;
import com.epam.auctions.repository.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository<User> {

    @Language("MySQL")
    private static final String SQL_CREATE_USER =
            "INSERT INTO users(id, username, email, password, first_name, middle_name," +
                    " last_name, user_role_id, user_status_id) " +
                    "VALUE (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Language("MySQL")
    private static final String SQL_SELECT_USER =
            "SELECT id, username, email, password, first_name, middle_name, last_name," +
                    " user_role_id, user_status_id FROM users";

    @Language("SQL")
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET username=?, email=?, first_name=?, middle_name=?, " +
                    "last_name=?, user_role_id=?, user_status_id=? WHERE id=?";

    @Language("SQL")
    private static final String SQL_COUNT_USER = "SELECT COUNT(*) FROM users";

    @Language("SQL")
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";

    private static final ResultSetHandler<Optional<User>> userResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER);

    private static final ResultSetHandler<List<User>> usersResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Integer> userIdSetHandler = rs -> {
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    };

    @Override
    @Transactional
    public User insert(User entity) {
        Integer id = JDBCUtils.insert(JDBCUtils.getConnection(),
                SQL_CREATE_USER, userIdSetHandler,
                entity.getUsername(), entity.getEmail(),
                entity.getPassword(), entity.getFirstName(),
                entity.getMiddleName(), entity.getLastName(),
                entity.getUserRole().getId(), entity.getUserStatus().getId());
        entity.setId(id);
        return entity;
    }

    @Override
    @Transactional
    public boolean update(User entity) {
        JDBCUtils.insert(JDBCUtils.getConnection(), SQL_UPDATE_USER, userIdSetHandler,
                entity.getUsername(), entity.getEmail(), entity.getFirstName(),
                entity.getMiddleName() == null ? null : entity.getMiddleName(),
                entity.getLastName(), entity.getUserRole().getId(),
                entity.getUserStatus().getId(), entity.getId());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(User entity) {
        return JDBCUtils.delete(JDBCUtils.getConnection(), SQL_DELETE_USER, entity.getId());
    }

    @Override
    @Transactional
    public long count(SqlSpecification specification, Object... parameters) throws RepositoryException {
        return JDBCUtils.count(JDBCUtils.getConnection(),
                specification.getSql(SQL_COUNT_USER),
                parameters);
    }

    @Override
    @Transactional(read = true)
    public Optional<User> select(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(),
                specification.getSql(SQL_SELECT_USER),
                userResultSetHandler,
                parameters);
    }

    @Override
    @Transactional
    public Collection<User> selectAll(SqlSpecification specification, Object... parameters) {
        return JDBCUtils.select(JDBCUtils.getConnection(),
                specification.getSql(SQL_SELECT_USER),
                usersResultSetHandler,
                parameters);
    }
}
