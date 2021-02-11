<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/19/21
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="com.epam.auctions.entity.LotStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="form" value="${requestScope.searchForm}" scope="request"/>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
<%-- Search form --%>
<form class="search" action="/feed" method="get">
    <div id="findProducts" class="panel panel-success">
        <div class="panel-heading"><fmt:message key="aside.search" /></div>
        <div class="panel-body">
            <div class="input-group">
                <input type="hidden" name="command" value="search">
                <input type="hidden" name="status" value="${LotStatus.ACTIVE.id}">
                <input type="text" name="query" class="form-control" placeholder="<fmt:message key="aside.search-query" />" value="${form.query}">
                <span class="input-group-btn">
					<button type="submit" id="goSearch" class="btn btn-default"><fmt:message key="aside.find" /></button>
				</span>
            </div>
        </div>
    </div>
</form>
