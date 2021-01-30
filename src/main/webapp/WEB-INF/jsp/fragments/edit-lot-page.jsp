<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/27/21
  Time: 12:05 AM
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
    <link rel="stylesheet" href="/static/styles/index.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous"/>
    <link rel="icon" href="/static/media/icons/favicon16.png" sizes="16x16" type="image/png"/>
    <link rel="icon" href="/static/media/icons/favicon32.png" sizes="32x32" type="image/png"/>
    <title><fmt:message key="lot.create.title"/></title>
    <script src="https://jsuites.net/v3/jsuites.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<c:set var="lot" value="${requestScope.lot}" scope="request" />
<div class="container-xxl h-100">
    <form class="was-validated" id="edit-lot-form" method="post" action="/feed?command=update-lot&lotId=${lot.id}">
        <div class="mb-3">
            <label for="validationTextarea" class="form-label"><fmt:message key="lot.create.form.description"/></label>
            <textarea class="form-control" id="validationTextarea" name="lot-title"
                      maxlength="100" style="resize: none; height: 40px" required>${lot.description}</textarea>
            <div class="invalid-feedback">
                <fmt:message key="lot.create.form.description.invalid"/>
            </div>
        </div>

        <div class="mb-3">
            <label for="validationNumeric" class="form-label">Minimal bid</label>
            <input id="validationNumeric" class="form-control" type="text" value="${lot.minimalBid}" data-mask='0,00' name="minimal-bid" required />
            <div class="invalid-feedback">
                Ставка не может быть меньше $0.10
            </div>
        </div>

        <div class="mb-3">
            <label for="validationSelect" class="form-label"><fmt:message key="lot.create.form.duration"/></label>
            <select id="validationSelect" class="form-select" required
                    name="lot-time-duration" >
                <option value="1" <c:if test="${lot.durationInMinutes eq 30}">selected</c:if>><fmt:message key="lot.create.form.duration.30min"/></option>
                <option value="2" <c:if test="${lot.durationInMinutes eq 60}">selected</c:if>><fmt:message key="lot.create.form.duration.1hour"/></option>
                <option value="3" <c:if test="${lot.durationInMinutes eq 120}">selected</c:if>><fmt:message key="lot.create.form.duration.2hours"/></option>
                <option value="4" <c:if test="${lot.durationInMinutes eq 1440}">selected</c:if>><fmt:message key="lot.create.form.duration.24hours"/></option>
                <option value="5" <c:if test="${lot.durationInMinutes eq 2880}">selected</c:if>><fmt:message key="lot.create.form.duration.48hours"/></option>
            </select>
            <div class="invalid-feedback"><fmt:message key="lot.create.form.duration.invalid"/></div>
        </div>
        <div class="mb-3">
            <button class="btn btn-primary" type="submit"><fmt:message
                    key="lot.create.form.submit"/></button>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
<script src="/static/js/ajax/editLotPage.js"></script>
</body>
</html>
