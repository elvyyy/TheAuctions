package com.epam.auctions.repository;

import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
import com.epam.auctions.entity.UserStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ResultSetHandlerFactory {

    public static final ResultSetHandler<User> USER_RESULT_SET_HANDLER = resultSet -> {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setMiddleName(resultSet.getString("middle_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setUserRole(UserRole.fromId(resultSet.getInt("user_role_id")));
        user.setUserStatus(UserStatus.fromId(resultSet.getInt("user_status_id")));
        return user;
    };

    public static final ResultSetHandler<Lot> LOT_RESULT_SET_HANDLER = resultSet -> {
        Lot lot = new Lot();
        lot.setId(resultSet.getInt("id"));
        lot.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        lot.setCreatedBy(resultSet.getInt("created_by"));
        lot.setVerifiedBy(resultSet.getInt("verified_by"));
        lot.setMinimalBid(resultSet.getBigDecimal("minimal_bid"));
        lot.setLotStatus(LotStatus.fromId(resultSet.getInt("lot_status_id")));
        lot.setDescription(resultSet.getString("description"));
        final int soldTo = resultSet.getInt("sold_to");
        lot.setSoldTo(soldTo == 0 ? null : soldTo);
        lot.setPhotoPath(resultSet.getString("image_url"));
        final Timestamp endAt = resultSet.getTimestamp("end_at");
        lot.setEndAt(endAt == null ? null : endAt.toLocalDateTime());
        final Timestamp startAt = resultSet.getTimestamp("start_at");
        lot.setStartAt(startAt == null ? null : startAt.toLocalDateTime());
        lot.setDurationInMinutes(resultSet.getInt("duration"));
        return lot;
    };

    public static final <T> ResultSetHandler<Optional<T>> getSingleResultSetHandler(ResultSetHandler<T> resultSetHandler) {
        return (resultSet) -> {
            if (resultSet.next()) {
                return Optional.of(resultSetHandler.handle(resultSet));
            }
            return Optional.empty();
        };
    }

    public static final <T> ResultSetHandler<List<T>> getListResultSetHandler(ResultSetHandler<T> resultSetHandler) {
        return resultSet -> {
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSetHandler.handle(resultSet));
            }
            return list;
        };
    }
}
