package com.epam.auctions.repository.specification;

import java.util.Arrays;

public class SqlSpecificationUtils {
    public static SqlSpecification concat(SqlSpecification... specifications) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SqlSpecification specification : specifications) {
            stringBuilder.append(specification.getSpecification()).append(' ');
        }
        return new WrapSpecification(stringBuilder.toString().trim());
    }
}
