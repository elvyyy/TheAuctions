package com.epam.auctions.repository.specification;

public interface SqlSpecification {
    String getSql(String baseSql);
    Object[] getParameters();
    String getSpecification();
}
