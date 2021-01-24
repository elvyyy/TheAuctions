package com.epam.auctions.repository.specification;

public class WrapSpecification implements SqlSpecification{
    private String sql;

    WrapSpecification(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql(String baseSql) {
        return String.join(" ", baseSql, sql);
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public String getSpecification() {
        return sql;
    }
}
