package com.epam.auctions.validator;

import com.epam.auctions.entity.Entity;
import com.epam.auctions.exception.FormValidationException;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Validator.
 *
 * @param <T> the type parameter
 */
public interface Validator<T extends Entity<? extends Number>> {
    /**
     * Validates {@code HttpServletRequest}.
     *
     * @param req the req
     * @return the t
     * @throws FormValidationException if the validation failed
     */
    T validate(HttpServletRequest req) throws FormValidationException;
}
