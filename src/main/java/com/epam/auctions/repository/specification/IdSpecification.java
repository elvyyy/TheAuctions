package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

/**
 * The type Id specification.
 */
public class IdSpecification implements SqlSpecification {
    @Language("SQL")
    private final String SQL_SPECIFICATION = "WHERE id=?";

    private Object[] parameters;

    /**
     * Instantiates a new Id specification.
     */
    public IdSpecification() {
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
