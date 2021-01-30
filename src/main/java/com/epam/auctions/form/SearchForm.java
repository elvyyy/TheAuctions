package com.epam.auctions.form;

import javax.servlet.http.HttpServletRequest;

public class SearchForm {
    private String query;

    private String lotStatusId;

    public SearchForm(String query) {
        this.query = query;
    }

    public SearchForm(String query, String lotStatusId) {
        this.query = query;
        this.lotStatusId = lotStatusId;
    }

    public String getLotStatusId() {
        return lotStatusId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public static SearchForm createFrom(HttpServletRequest request) {
        String query = request.getParameter("query");
        if (query == null) {
            query = "";
        }
        return new SearchForm(query, request.getParameter("status"));
    }
}
