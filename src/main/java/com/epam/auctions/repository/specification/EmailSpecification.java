package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

/**
 * The type Email specification.
 */
public class EmailSpecification implements SqlSpecification {
    @Language("SQL")
    private final String SQL_SPECIFICATION = "WHERE email=?";

    private Object[] parameters;

    /**
     * Instantiates a new Email specification.
     */
    public EmailSpecification() {
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
