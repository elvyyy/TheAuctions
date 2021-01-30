<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/25/21
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<div class="container-xxl h-100">
    <div class="d-flex justify-content-center">
        <form method="post" action="/feed?command=update-user-profile" onsubmit="return validate();" style="width: 400px"
              class="signup-form" novalidate="">
            <div class="form-row">
                <div class="row">
                    <div class="col-md-6 mb-6">
                        <label for="first_name"><fmt:message key="signup.form.firstname"/></label>
                        <input type="text" class="form-control" id="first_name"
                               value="${user.firstName}" name="first-name" required="">
                        <div class="invalid-feedback">
                            <fmt:message key="signup.form.firstname.invalid"/>
                        </div>
                    </div>
                    <div class="col-md-6 mb-6">
                        <label for="last_name"><fmt:message key="signup.form.lastname"/></label>
                        <input type="text" class="form-control" id="last_name"
                               value="${user.lastName}" name="last-name" required="">
                        <div class="invalid-feedback">
                            <fmt:message key="signup.form.lastname.invalid"/>
                        </div>
                    </div>
                </div>
                <div>
                    <label for="middle_name"><fmt:message key="signup.form.middlename"/></label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="middle_name"
                               value="${user.middleName}" name="middle-name"
                               aria-describedby="inputGroupPrepend3">
                        <div class="invalid-feedback">
                            <fmt:message key="signup.form.middlename.invalid"/>
                        </div>
                    </div>
                </div>
                <div class="">
                    <label for="username"><fmt:message key="signup.form.username"/></label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="username"
                               value="${user.username}" name="username"
                               aria-describedby="inputGroupPrepend3" required="">
                        <div class="invalid-feedback invalid-username-match">
                            <fmt:message key="signup.form.username.invalid"/>
                        </div>
                        <div class="invalid-feedback username-in-use d-none">
                            <fmt:message key="signup.form.username.inuse"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="">
                    <label for="email"><fmt:message key="signup.form.email"/></label>
                    <input type="email" class="form-control" id="email"
                           value="${user.email}" name="email" required="" readonly>
                    <div class="invalid-feedback invalid-email-match">
                        <fmt:message key="signup.form.email.invalid"/>
                    </div>
                    <div class="invalid-feedback email-in-use d-none">
                        <fmt:message key="signup.form.email.inuse"/>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary" type="submit"><fmt:message key="update.save"/></button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
<script src="/static/js/validators/userEditProfileValidator.js"></script>
<script src="/static/js/ajax/changelanguage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>
