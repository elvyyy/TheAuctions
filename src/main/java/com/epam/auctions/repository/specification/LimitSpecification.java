package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

public class LimitSpecification implements SqlSpecification{
    @Language("SQL")
    private final String SQL_SPECIFICATION = "LIMIT ? OFFSET ?";

    @Override
    public String getSql(String baseSql) {
        return null;
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public String getSpecification() {
        return SQL_SPECIFICATION;
    }
}
