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
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<div class="container">

    <h1>Shopping Cart</h1>
    <hr>
    <table class="table table-striped table-hover table-bordered">
        <tbody>
        <tr>
            <th>Item</th>
            <th>Конец</th>
            <th>Created by</th>
            <th>Min Price</th>
        </tr>
        <tr>
            <td>${lot.description}</td>
            <td><time:localDateTime value="${lot.endAt}" format="short"/></td>
            <td></td>
            <td>${lot.minimalBid}</td>
        </tr>
        <tr>
            <th colspan="3"><span class="pull-right">Текущая ставка</span></th>
            <th id="currentBid">
                <c:choose>
                    <c:when test="${not empty bid}">${bid.currentBid}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </th>
        </tr>
        <tr>
            <th colspan="3"><span class="pull-right">Возможный победитель</span></th>
            <th id="currentBidCreatedBy">
                <c:choose>
                    <c:when test="${not empty bid}">${bid.createdBy}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </th>
        </tr>
        <tr>
            <th colspan="3"><span class="pull-right">Total</span></th>
            <th>£300.00</th>
        </tr>
        <tr>
            <td><a href="#" class="btn btn-primary">Continue Shopping</a></td>
            <td colspan="3"><a href="#" class="pull-right btn btn-success">Checkout</a></td>
        </tr>
        </tbody>
    </table>

</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
