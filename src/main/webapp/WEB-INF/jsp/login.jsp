<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/15/21
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8;" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" href="/static/styles/index.css">
    <link rel="stylesheet" href="/static/styles/login.css">
    <link rel="stylesheet" href="/static/styles/loginUtil.css">
    <link rel="icon" href="static/media/icons/favicon16.png" type="image/png" sizes="16x16">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
            <form class="login100-form validate-form" method="post" action="/join?command=login">
					<span class="login100-form-title p-b-33">
						<fmt:message key="login.form.title"/>
					</span>
                <div class="wrap-input100 validate-input"
                     data-validate="<fmt:message key="signup.form.email.invalid"/>">
                    <input class="input100" type="email" name="email" placeholder="<fmt:message key="login.email"/>"
                           required>
                    <span class="focus-input100-1"></span>
                    <span class="focus-input100-2"></span>
                </div>
                <div class="wrap-input100 rs1 validate-input" data-validate="<fmt:message key="login.field.required"/>">
                    <input class="input100" type="password" name="password"
                           placeholder="<fmt:message key="signup.form.password"/>" required>
                    <span class="focus-input100-1"></span>
                    <span class="focus-input100-2"></span>
                </div>
                <div class="container-login100-form-btn m-t-20">
                    <button class="login100-form-btn">
                        <fmt:message key="main.login"/>
                    </button>
                </div>
                <div class="text-center p-t-45 p-b-4">
						<span class="txt1">
							<fmt:message key="login.field.create-account"/>
						</span>

                    <a href="/join?command=get-signup" class="txt2 hov1">
                        <fmt:message key="main.signup"/>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/static/js/ajax/changelanguage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>

