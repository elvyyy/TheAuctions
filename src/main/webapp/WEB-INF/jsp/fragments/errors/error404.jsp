<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/styles/footer.css">
    <link rel="stylesheet" href="/static/styles/404error.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="icon" href="/static/media/icons/favicon16.png" sizes="16x16" type="image/png">
    <link rel="icon" href="/static/media/icons/favicon32.png" sizes="32x32" type="image/png">
    <title><fmt:message key="error.label.404.title"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1>4<span></span>4</h1>
        </div>
        <h2><fmt:message key="error.label.404.body.title" /></h2>
        <p><fmt:message key="error.label.404.body.description" /></p>
        <a href="/"><fmt:message key="error.label.404.body.backTo"/></a>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
