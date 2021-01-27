<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/22/21
  Time: 4:48 PM
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
    <div id="lot-list">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="non-verified-lots.user.table.first-column-name"/></th>
                <th><fmt:message key="non-verified-lots.user.table.second-column-name"/></th>
                <th><fmt:message key="non-verified-lots.user.table.third-column-name"/></th>
                <th><fmt:message key="non-verified-lots.user.table.fourth-column-name"/></th>
                <th><fmt:message key="non-verified-lots.user.table.fifth-column-name"/></th>
                <th><fmt:message key="non-verified-lots.user.table.sixth-column-name"/></th>
                <th><fmt:message key="lots-page.table.create-at"/></th>
                <th><fmt:message key="lots-page.table.ended-at"/></th>
                <th><fmt:message key="lots-page.table.lot-status"/></th>
            </tr>
            </thead>
            <tbody>
            <jsp:include page="lots.jsp" />
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
