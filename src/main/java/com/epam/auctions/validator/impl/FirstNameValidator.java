package com.epam.auctions.validator.impl;

import com.epam.auctions.command.RequestContext;
import com.epam.auctions.validator.AbstractValidator;
import com.epam.auctions.validator.Validator;
import com.epam.auctions.validator.Violation;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class FirstNameValidator extends AbstractValidator {

    public static final String FIRST_NAME_PATTERN = "";

    public FirstNameValidator(Validator validator) {
        super(validator);
    }

    @Override
    public Set<Violation> apply(RequestContext context) {
//        Set<Violation> result = new HashSet<>();
//        Violation violation = new Violation(MessageManager.getMessage("violation.firstname",
//                (String) context.getSessionAttribute(LOCALE)));
//
//        if (!context.getRequestParameters().containsKey(RequestConstant.FIRSTNAME)) {
//            log.warn("Invalid content parameter: no firstname parameter in request");
//            result.add(violation);
//        } else if (!context.getParameter(FIRST_NAME.value()).matches(FIRSTNAME_REGEX_PATTERN)) {
//            log.warn("Invalid content parameter: " + context.getParameter(FIRST_NAME.value()));
//            result.add(violation);
//        }
//
//        if ( != null) {
//            result.addAll(super.apply(context));
//
//        }
        return null;
    }
}
