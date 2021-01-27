package com.epam.auctions.validator.impl;

import com.epam.auctions.constant.Constants;
import com.epam.auctions.entity.Entity;
import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
import com.epam.auctions.entity.UserStatus;
import com.epam.auctions.exception.FormValidationException;
import com.epam.auctions.validator.Validator;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class UserValidator implements Validator {

    /**
     * Password pattern string
     * Minimum eight characters, maximum 36 characters, at least one letter and one number:
     */
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,36}$";

    public UserValidator() {
    }

    @Override
    public Entity<? extends Number> validate(HttpServletRequest req) throws FormValidationException {
        User user = new User();
        Pattern passwordPattern = Pattern.compile(UserValidator.passwordPattern);
        user.setFirstName(req.getParameter(Constants.FIRST_NAME.value()));
        user.setMiddleName(req.getParameter(Constants.MIDDLE_NAME.value()));
        user.setLastName(req.getParameter(Constants.LAST_NAME.value()));
        user.setUsername(req.getParameter(Constants.USERNAME.value()));
        user.setEmail(req.getParameter(Constants.EMAIL.value()));
        String parameter = req.getParameter(Constants.PASSWORD.value());
        String confirmPassword = req.getParameter(Constants.CONFIRM_PASSWORD.value());
        if (parameter != null && confirmPassword != null) {
            if (passwordPattern.matcher(parameter).matches() && parameter.equals(confirmPassword)) {
                String hashedPassword = BCrypt.hashpw(parameter, BCrypt.gensalt());
                user.setPassword(hashedPassword);
            } else {
                throw new FormValidationException(Constants.PASSWORD.value(), parameter);
            }
        } else {
            throw new FormValidationException(Constants.EMAIL.value(), parameter);
        }
        user.setUserRole(UserRole.USER);
        user.setUserStatus(UserStatus.REGISTERED);
        return user;
    }
}
