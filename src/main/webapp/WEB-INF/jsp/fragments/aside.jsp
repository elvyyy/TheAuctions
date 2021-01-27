<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/19/21
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="visible-xs-block xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#productCatalog">Product catalog <span class="caret"></span></a>
    <a data-toggle="collapse" href="#findProducts">Find products <span class="caret"></span></a>
</div>
<%-- Search form --%>
<form class="search" action="/search">
    <div id="findProducts" class="panel panel-success collapse">
        <div class="panel-heading">Find lots</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search query">
                <span class="input-group-btn">
					<a id="goSearch" class="btn btn-default">Go!</a>
				</span>
            </div>
            <div class="more-options">
                <a data-toggle="collapse" href="#searchOptions">More filters <span class="caret"></span></a>
            </div>
        </div>
        <%--        <div id="searchOptions" class="collapse ${searchForm.categoriesNotEmpty or searchForm.producersNotEmpty ? 'in' : '' }">--%>
        <%--            <ishop:category-filter categories="${CATEGORY_LIST }" searchForm="${searchForm}" />--%>
        <%--            <ishop:producer-filter producers="${PRODUCER_LIST }"  searchForm="${searchForm}" />--%>
        <%--        </div>--%>
    </div>
</form>
<%-- /Search form --%>
<%-- Categories --%>
<div id="productCatalog" class="panel panel-success collapse">
    <div class="panel-heading">Product catalog</div>
    <div class="list-group">
        <%--        <c:forEach var="category" items="${CATEGORY_LIST }">--%>
        <%--            <a href="/products${category.url }" class="list-group-item ${selectedCategoryUrl == category.url ? 'active' : '' }">--%>
        <%--                <span class="badge">${category.productCount}</span> ${category.name}--%>
        <%--            </a>--%>
        <%--        </c:forEach>--%>
        <a href="/products"
           class="list-group-item active">
            <span class="badge">5</span> Носков
        </a>
    </div>
</div>
