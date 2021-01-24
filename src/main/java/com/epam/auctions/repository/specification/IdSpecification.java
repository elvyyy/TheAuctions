package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

public class IdSpecification implements SqlSpecification{
    @Language("SQL")
    private final String SQL_SPECIFICATION = "WHERE id=?";

    private Object[] parameters;

    public IdSpecification() {
    }

    @Override
    public String getSpecification() {
        return SQL_SPECIFICATION;
    }

    @Override
    public String getSql(String baseSql) {
        return String.join(" ", baseSql, SQL_SPECIFICATION);
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }
}
