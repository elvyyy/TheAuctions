<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/19/21
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="time" uri="/WEB-INF/tags.tld" %>
<c:forEach var="lot" items="${lots}">
    <tr>
        <th scope="row"><a href="/feed?command=get-lot&l=${lot.id}">${lot.id}</a></th>
        <td><img src="${lot.photoPath}" height="150" width="150" alt="default"></td>
        <td>${lot.description}</td>
        <td>${lot.minimalBid}</td>
        <td><time:localDateTime value="${lot.startAt}" format="short"/></td>
        <td><time:localDateTime value="${lot.endAt}" format="short"/></td>
    </tr>
</c:forEach>