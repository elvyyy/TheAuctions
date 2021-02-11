package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

/**
 * The type Username specification.
 */
public class UsernameSpecification implements SqlSpecification {

    @Language("SQL")
    private final String SQL_SPECIFICATION = "WHERE username=?";

    private Object[] parameters;

    /**
     * Instantiates a new Username specification.
     */
    public UsernameSpecification() {
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }

    @Override
    public String getSpecification() {
        return SQL_SPECIFICATION;
    }

    @Override
    public String getSql(String baseSql) {
        return String.join(" ", baseSql, SQL_SPECIFICATION);
    }
}
