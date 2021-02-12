<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/29/21
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="time" uri="http://mycompany.com" %>
<%@ page import="com.epam.auctions.entity.LotStatus" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
<c:set var="user" value="${sessionScope.user}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="static/styles/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="icon" href="/static/media/icons/favicon16.png" sizes="16x16" type="image/png">
    <link rel="icon" href="/static/media/icons/favicon32.png" sizes="32x32" type="image/png">
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<c:set var="lot" value="${requestScope.lot}" scope="request"/>
<c:set var="bid" value="${requestScope.bid}" scope="request"/>
<c:set var="user" value="${sessionScope.user}" scope="request"/>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<div class="container">
    <c:if test="${lot.lotStatus eq LotStatus.COMPLETED}">
        <div class="modal fade" id="change_status_modal" data-bs-target="#change_status_modal" tabindex="-1"
             role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            <fmt:message key="lot.completed.status" />
                        </h5>
                    </div>
                    <div class="modal-body">
                        <small class="success-change-role">
                            <c:choose>
                                <c:when test="${not empty bid and bid.createdBy eq user.username}">
                                    <fmt:message key="lot.completed.winner" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="lot.completed.closed" />
                                </c:otherwise>
                            </c:choose>
                        </small>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                key="admin.panel.close"/></button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <h1><fmt:message key="lot.number"/> №${lot.id}</h1>
    <input type="hidden" id="lotId" value="${lot.id}">
    <hr>
    <table class="table table-striped table-hover table-bordered">
        <tbody>
        <tr>
            <th><fmt:message key="lot.create.form.description"/></th>
            <th><fmt:message key="lots-page.table.ended-at"/></th>
            <th><fmt:message key="lots-page.table.sixth-column"/></th>
            <th><fmt:message key="lots-page.table.fifth-column"/></th>
        </tr>
        <tr>
            <td>${lot.description}</td>
            <td><time:localDateTime value="${lot.endAt}" format="short"/></td>
            <td><time:localDateTime value="${lot.startAt}" format="short"/></td>
            <td>${lot.minimalBid}</td>
        </tr>
        <tr>
            <th colspan="3"><span class="pull-right"><fmt:message key="lot-page.current-bid"/></span></th>
            <th id="currentBid">
                <c:choose>
                    <c:when test="${not empty bid}">${bid.currentBid}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </th>
        </tr>
        <tr>
            <th colspan="3"><span class="pull-right"><fmt:message key="lot-page.probable-winner"/></span></th>
            <th id="currentBidCreatedBy">
                <c:choose>
                    <c:when test="${not empty bid}">${bid.createdBy}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </th>
        </tr>
        <tr>
            <td><a href="/feed?command=get-lots" class="btn btn-primary"><fmt:message key="lot-page.feed" /></a></td>
            <td colspan="3">
                <a href="#" class="pull-right btn btn-success make-bid-btn" bid="1"><fmt:message
                        key="lot-page.up10"/></a>
                <a href="#" class="pull-right btn btn-success make-bid-btn" bid="2"><fmt:message
                        key="lot-page.up20"/></a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
<script src="/static/js/ajax/lotPage.js"></script>
</body>
</html>
