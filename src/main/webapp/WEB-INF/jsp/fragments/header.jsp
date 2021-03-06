<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/12/21
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.epam.auctions.entity.UserRole" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
<div class="container-xxl main-header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid justify-content-between">
            <a class="navbar-brand" href="/">TheAuctions</a>
            <div class="" id="navbarText">
                <div class="button-group">
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <a href="/join?command=get-login" class="btn btn-light" type="button"><fmt:message
                                    key="main.login"/></a>
                            <a href="/join?command=get-signup" class="btn btn-light" type="button"><fmt:message
                                    key="main.signup"/></a>
                        </c:when>
                        <c:when test="${not empty sessionScope.user}">
                            <a href="/feed?command=get-lots" class="btn btn-light" type="button"><fmt:message key="header.feed" /></a>
                            <a href="/feed?command=get-user-lots" class="btn btn-light" type="button"><fmt:message key="header.my-lots" /></a>
                            <a href="/feed?command=get-profile" class="btn btn-light" type="button"><fmt:message key="header.profile" /></a>
                            <a href="/feed?command=get-create-lot" class="btn btn-light" type="button"><fmt:message
                                    key="lot.create.new-lot"/></a>
                            <c:if test="${sessionScope.user.userRole eq UserRole.ADMIN}">
                                <a href="/feed?command=get-non-verified-lots" class="btn btn-light" type="button"><fmt:message key="header.admin.new-lots" /></a>
                                <a href="/feed?command=get-admin-panel" class="btn btn-light" type="button"><fmt:message key="header.admin.panel" /></a>
                            </c:if>
                            <a href="/join?command=logout" class="btn btn-light" type="button"><fmt:message
                                    key="main.logout"/></a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>
</div>