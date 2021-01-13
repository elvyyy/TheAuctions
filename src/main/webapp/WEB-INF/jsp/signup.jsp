<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12/22/20
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }" scope="session"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="signup.title"/></title>
    <link rel="stylesheet" href="../../static/styles/index.css">
    <link rel="icon" href="static/media/icons/favicon16.png" type="image/png" sizes="16x16">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container-xxl" style="position: relative">
    <form method="post" action="/join?command=signup" style="width: 400px" class="signup-form">
        <div class="form-row">
            <div class="row">
                <div class="col-md-6 mb-6">
                    <label for="first_name"><fmt:message key="signup.form.firstname"/></label>
                    <input type="text" class="form-control" id="first_name" placeholder="<fmt:message key="signup.form.firstname"/>" name="first-name" required>
                    <div class="valid-tooltip">
                        Looks good!
                    </div>
                </div>
                <div class="col-md-6 mb-6">
                    <label for="last_name"><fmt:message key="signup.form.lastname"/></label>
                    <input type="text" class="form-control" id="last_name" placeholder="<fmt:message key="signup.form.lastname"/>" name="last-name" required>
                    <div class="valid-tooltip">
                        Looks good!
                    </div>
                </div>
            </div>
            <div>
                <label for="middle_name"><fmt:message key="signup.form.middlename"/></label>
                <div class="input-group">
                    <input type="text" class="form-control" id="middle_name" placeholder="<fmt:message key="signup.form.middlename"/>" name="middle-name"
                           aria-describedby="inputGroupPrepend3">
                    <div class="invalid-tooltip">
                        Выберите имя пользователя
                    </div>
                </div>
            </div>
            <div class="">
                <label for="username"><fmt:message key="signup.form.username"/></label>
                <div class="input-group">
                    <input type="text" class="form-control" id="username" placeholder="<fmt:message key="signup.form.username"/>" name="username"
                           aria-describedby="inputGroupPrepend3" required>
                    <div class="invalid-feedback invisible">
                        Выберите имя пользователя
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="">
                <label for="email"><fmt:message key="signup.form.email"/></label>
                <input type="email" class="form-control" id="email" placeholder="<fmt:message key="signup.form.email"/>" name="email" required>
                <div class="invalid-feedback">
                    Please provide a valid state.
                </div>
            </div>
            <div class="">
                <label for="password"><fmt:message key="signup.form.password"/></label>
                <input type="password" class="form-control" id="password" placeholder="<fmt:message key="signup.form.password"/>" name="password" required>
                <div class="invalid-feedback">
                    Please provide a valid city.
                </div>
            </div>
            <div class="">
                <label for="confirm_password"><fmt:message key="signup.form.confirm-password"/></label>
                <input type="password" class="form-control" id="confirm_password" placeholder="<fmt:message key="signup.form.confirm-password"/>" name="confirm-password"
                       required>
                <div class="invalid-feedback">
                    Please provide a valid state.
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="invalidCheck3" name="form-check" required>
                <label class="form-check-label" for="invalidCheck3">
                    <fmt:message key="signup.form.agreement"/>
                </label>
                <div class="invalid-feedback">
                    You must agree before submitting.
                </div>
            </div>
        </div>
        <button class="btn btn-primary" type="submit"><fmt:message key="signup.form.submit"/></button>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="static/ajax/changelanguage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>
