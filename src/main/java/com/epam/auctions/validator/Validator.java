package com.epam.auctions.validator;

import com.epam.auctions.entity.Entity;
import com.epam.auctions.exception.FormValidationException;

import javax.servlet.http.HttpServletRequest;

public interface Validator<T extends Entity<? extends Number>> {
    T validate(HttpServletRequest req) throws FormValidationException;
}
