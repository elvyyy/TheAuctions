package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

public class EmailSpecification implements SqlSpecification {
    @Language("SQL")
    private final String SQL_SPECIFICATION = "WHERE email=?";

    private Object[] parameters;

    public EmailSpecification() {
    }

    @Override
    public String getSql(String baseSql) {
        return String.join(" ", baseSql, SQL_SPECIFICATION);
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }

    @Override
    public String getSpecification() {
        return SQL_SPECIFICATION;
    }
}
