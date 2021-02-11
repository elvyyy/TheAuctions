package com.epam.auctions.form;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Search form.
 */
public class SearchForm {
    /**
     * lot status id
     */
    private String lotStatusId;
    /**
     * search query
     */
    private String query;

    /**
     * Instantiates a new Search form.
     *
     * @param query the query
     */
    public SearchForm(String query) {
        this.query = query;
    }

    /**
     * Instantiates a new Search form.
     *
     * @param query       the query
     * @param lotStatusId the lot status id
     */
    public SearchForm(String query, String lotStatusId) {
        this.query = query;
        this.lotStatusId = lotStatusId;
    }

    /**
     * Creates search form from {@code HttpServletRequest}.
     *
     * @param request the request
     * @return the search form
     */
    public static SearchForm createFrom(HttpServletRequest request) {
        String query = request.getParameter("query");
        if (query == null) {
            query = "";
        }
        return new SearchForm(query, request.getParameter("status"));
    }

    /**
     * Gets lot status id.
     *
     * @return the lot status id
     */
    public String getLotStatusId() {
        return lotStatusId;
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets query.
     *
     * @param query the query
     */
    public void setQuery(String query) {
        this.query = query;
    }
}
