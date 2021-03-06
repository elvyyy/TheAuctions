package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

/**
 * The type Lot status specification.
 */
public class LotStatusSpecification implements SqlSpecification {
    @Language("SQL")
    private final String SQL_SPECIFICATION = "WHERE lot_status_id=?";

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
