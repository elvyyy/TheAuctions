package com.epam.auctions.dao;

import org.intellij.lang.annotations.Language;

public class UserDAO {

    @Language("SQL")
    private static final String SQL_INSERT_USER =
            "INSERT INTO application_user(login, password, is_admin, first_name, last_name, e_mail, gender, " +
                    "date_of_birth, active_status, verification_uuid, photo_path, paid_period) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Language("SQL")
    private static final String SQL_CREATE_USER =
            "INSERT INTO users(username, email, password, first_name, middle_name, last_name, user_role_id, user_status_id) " +
                    "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
}
