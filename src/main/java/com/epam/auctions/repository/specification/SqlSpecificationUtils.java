package com.epam.auctions.repository.specification;

/**
 * The type Sql specification utils.
 */
public class SqlSpecificationUtils {
    /**
     * Concats sql specifications.
     *
     * @param specifications the specifications
     * @return the sql specification
     */
    public static SqlSpecification concat(SqlSpecification... specifications) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SqlSpecification specification : specifications) {
            stringBuilder.append(specification.getSpecification()).append(' ');
        }
        return new WrapSpecification(stringBuilder.toString().trim());
    }
}
