package com.epam.auctions.repository.specification;

import org.intellij.lang.annotations.Language;

/**
 * The type Like specification.
 */
public class LikeSpecification implements SqlSpecification {
    @Language("SQL")
    private static final String SQL_SPECIFICATION =
            "WHERE description LIKE ? AND lot_status_id=?";

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
