<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/25/21
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.epam.auctions.entity.LotStatus" %>
<%@ taglib prefix="time" uri="/WEB-INF/tags.tld" %>
<c:forEach var="lot" items="${lots}">
    <tr id="lot${lot.id}">
        <th scope="row"><c:out value="${lot.id}"/></th>
        <td><img src="${lot.photoPath}" height="150" width="150" alt="default"/></td>
        <td>${lot.description}</td>
        <td>${lot.durationInMinutes}</td>
        <td>${lot.minimalBid}</td>
        <td>${lot.createdAt}</td>
        <td>
            <c:choose>
                <c:when test="${not empty lot.startAt}">
                    <time:localDateTime value="${lot.startAt}" format="short"/>
                </c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty lot.endAt}">
                    <time:localDateTime value="${lot.endAt}" format="short"/>
                </c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
        </td>
        <td>
            <c:choose>
                <c:when test="${lot.lotStatus eq LotStatus.CREATED}">
                    <form action="/feed" method="GET">
                        <input type="hidden" readonly name="command" value="edit-lot"/>
                        <input type="hidden" readonly name="lotId" value="${lot.id}"/>
                        <button class="btn btn-success" type="submit" id="lot-edit-button">Edit</button>
                    </form>
                </c:when>
                <c:otherwise>
                    ${lot.lotStatus}
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</c:forEach>
