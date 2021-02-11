package com.epam.auctions.repository.specification;

/**
 * The type Wrap specification.
 */
public class WrapSpecification implements SqlSpecification {
    private final String sql;

    /**
     * Instantiates a new Wrap specification.
     *
     * @param sql the sql
     */
    WrapSpecification(String sql) {
        this.sql = sql;
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public String getSpecification() {
        return sql;
    }

    @Override
    public String getSql(String baseSql) {
        return String.join(" ", baseSql, sql);
    }
}
