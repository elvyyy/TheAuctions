package com.epam.auctions.validator;

import com.epam.auctions.command.RequestContext;

import java.util.Set;
import java.util.function.Function;

public interface Validator extends Function<RequestContext, Set<Violation>> {
}
