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
            "SELECT id, username, email, password, first_name, middle_name, last_name, user_role_id, user_status_id FROM users";

    @Language("MySQL")
    private static final String SQL_COUNT_USER = "SELECT COUNT(*) FROM users";

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
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    @Transactional
    public long count(SqlSpecification specification) throws RepositoryException {
        return JDBCUtils.count(JDBCUtils.getConnection(),
                specification.getSql(SQL_COUNT_USER),
                specification.getParameters());
    }

    @Override
    @Transactional(read = true)
    public Optional<User> select(SqlSpecification specification) {
//        try (Connection connection = DBConnectionPool.INSTANCE.getConnection()) {
//            return JDBCUtils.select(connection,
//                    specification.getSql(SQL_SELECT_USER),
//                    userResultSetHandler,
//                    specification.getParameters());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            // TODO: exception handling
//            return Optional.empty();
//        }
        return JDBCUtils.select(JDBCUtils.getConnection(),
                specification.getSql(SQL_SELECT_USER),
                userResultSetHandler,
                specification.getParameters());
    }

    @Override
    @Transactional
    public Collection<User> selectAll(SqlSpecification specification) {
        return JDBCUtils.select(JDBCUtils.getConnection(),
                specification.getSql(SQL_SELECT_USER),
                usersResultSetHandler,
                specification.getParameters());
    }
}
