package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

/**
 * The type Lot id specification.
 */
public class LotIdSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String SQL_SPECIFICATION = "WHERE lot_id=?";

    @Override
    public Object[] getParameters() {
        return new Object[0];
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
