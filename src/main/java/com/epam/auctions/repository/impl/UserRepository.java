package com.epam.auctions.repository.impl;

import com.epam.auctions.db.impl.DBConnectionPool;
import com.epam.auctions.entity.User;
import com.epam.auctions.repository.Repository;
import com.epam.auctions.repository.ResultSetHandler;
import com.epam.auctions.repository.ResultSetHandlerFactory;
import com.epam.auctions.util.JDBCUtils;
import org.intellij.lang.annotations.Language;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository implements Repository<User> {

    @Language("SQL")
    private static final String SQL_CREATE_USER =
            "INSERT INTO users(id, username, email, password, first_name, middle_name," +
                    " last_name, user_role_id, user_status_id) " +
                    "VALUE (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        Integer id = JDBCUtils.insert(DBConnectionPool.INSTANCE.getConnection(),
                SQL_CREATE_USER, userIdSetHandler,
                entity.getUsername(), entity.getEmail(), entity.getPassword(), entity.getFirstName(),
                entity.getMiddleName(), entity.getLastName(), entity.getUserRole().getId(), entity.getUserStatus().getId());
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
}
