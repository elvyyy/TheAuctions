<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/22/21
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
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
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<div class="container-xxl">
    <div id="lot-list" data-page-count="${pageCount}" data-page-number="1">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="non-verified-lots.admin.table.first-column-name"/></th>
                <th><fmt:message key="non-verified-lots.admin.table.second-column-name"/></th>
                <th><fmt:message key="non-verified-lots.admin.table.third-column-name"/></th>
                <th><fmt:message key="non-verified-lots.admin.table.fourth-column-name"/></th>
                <th><fmt:message key="non-verified-lots.admin.table.fifth-column-name"/></th>
                <th><fmt:message key="non-verified-lots.admin.table.sixth-column-name"/></th>
                <th><fmt:message key="non-verified-lots.admin.table.seventh-column-name"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lot" items="${lots}">
                <tr id="lot${lot.id}">
                    <th scope="row"><c:out value="${lot.id}"/></th>
                    <td><img src="${lot.photoPath}" height="150" width="150" alt="default"/></td>
                    <td>${lot.description}</td>
                    <td>${lot.durationInMinutes}</td>
                    <td>$${lot.minimalBid}</td>
                    <td>
                        <button class="btn btn-success" id="acceptButton${lot.id}" onclick=""><fmt:message
                                key="non-verified-lots.admin.table.sixth-column-name"/></button>
                    </td>
                    <td>
                        <button class="btn btn-danger" id="cancelButton${lot.id}" onclick="cancel(this)"><fmt:message
                                key="non-verified-lots.admin.table.seventh-column-name"/></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
<script src="/static/js/ajax/lotCommand.js"></script>
</body>
</html>
