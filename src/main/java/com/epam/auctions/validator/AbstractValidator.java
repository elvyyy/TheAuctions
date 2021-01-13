package com.epam.auctions.validator;

import com.epam.auctions.command.RequestContext;

import java.util.Set;

public abstract class AbstractValidator implements Validator {
    private Validator validator;

    public AbstractValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Set<Violation> apply(RequestContext context) {
        return validator.apply(context);
    }
}
