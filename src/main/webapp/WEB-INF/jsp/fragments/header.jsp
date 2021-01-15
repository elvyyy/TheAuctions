<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/12/21
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }" scope="session"/>
<fmt:setBundle basename="message"/>
<div class="container-xxl main-header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid justify-content-between">
            <a class="navbar-brand" href="/">TheAuctions</a>
            <div class="" id="navbarText">
                <div class="button-group">
                    <a href="/join?command=get-login" class="btn btn-light" type="button"><fmt:message
                            key="main.login"/></a>
                    <a href="/join?command=get-signup" class="btn btn-light" type="button"><fmt:message
                            key="main.signup"/></a>
                </div>
            </div>
        </div>
    </nav>
</div>