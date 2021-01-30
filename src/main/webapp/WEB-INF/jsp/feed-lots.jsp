<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/19/21
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/styles/index.css">
    <link rel="stylesheet" href="/static/styles/lots.css">
    <link rel="icon" href="static/media/icons/favicon16.png" type="image/png" sizes="16x16">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<div class="container-xxl full-screen-container">
    <div class="row">
        <aside class="col-3">
            <jsp:include page="fragments/aside.jsp"/>
        </aside>
        <main class="col-9">
            <%--            <jsp:include page="${currentPage}"/>--%>
            <div id="lot-list" data-page-count="${pageCount}" data-page-number="1">
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="lots-page.table.first-column"/></th>
                        <th><fmt:message key="lots-page.table.second-column"/></th>
                        <th><fmt:message key="lots-page.table.third-column"/></th>
                        <th><fmt:message key="lots-page.table.fourth-column"/></th>
                        <th><fmt:message key="lots-page.table.fifth-column"/></th>
                        <th><fmt:message key="lots-page.table.sixth-column"/></th>
                        <th><fmt:message key="lots-page.table.seventh-column"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:include page="fragments/lots.jsp"/>
                    </tbody>
                </table>
                <c:if test="${pageCount > 1}">
                    <div class="text-center">
                        <a id="load-more" class="btn btn-success">Load More</a>
                    </div>
                </c:if>
            </div>
        </main>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
<script src="/static/js/ajax/app.js"></script>
</body>
</html>
