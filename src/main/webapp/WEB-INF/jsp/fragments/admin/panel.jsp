<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/25/21
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }"
               scope="session"/>
<fmt:setBundle basename="message"/>
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
    <div class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                    <fmt:message key="admin.panel.change-status"/>
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
                 data-bs-parent="#accordionFlushExample">
                <form class="row g-3 p-3" id="change_status_form">
                    <div class="col-auto">
                        <input type="text" name="username" pattern="^[a-zA-Z0-9_]{1,16}$" class="form-control"
                               id="change_status_username" placeholder="<fmt:message key="signup.form.username" />">
                    </div>
                    <div class="col-auto">
                        <select id="change_status_status" class="form-select" name="status">
                            <option selected value="1"><fmt:message key="admin.panel.status.registered"/></option>
                            <option value="0"><fmt:message key="admin.panel.status.banned"/></option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" id="change_status_submit" class="btn btn-primary mb-3"><fmt:message
                                key="admin.panel.change"/></button>
                    </div>
                </form>
            </div>
            <div class="modal fade" id="change_status_modal" data-bs-target="#change_username_model" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message
                                    key="lots-page.table.lot-status"/></h5>
                        </div>
                        <div class="modal-body">
                            <small class="success-change-status"><fmt:message
                                    key="admin.panel.success-change-status"/></small>
                            <small class="failure-change-status d-none"><fmt:message
                                    key="admin.panel.fail-change-status"/></small>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                    key="admin.panel.close"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                    <fmt:message key="admin.panel.change-role"/>
                </button>
            </h2>
            <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo"
                 data-bs-parent="#accordionFlushExample">
                <form class="row g-3 p-3" id="change_role_form">
                    <div class="col-auto">
                        <input type="text" name="username" pattern="^[a-zA-Z0-9_]{1,16}$" class="form-control"
                               id="change_role_username" placeholder="<fmt:message key="signup.form.username" />">
                    </div>
                    <div class="col-auto">
                        <select id="change_role_role" class="form-select" name="status">
                            <option selected value="0"><fmt:message key="admin.panel.role.user"/></option>
                            <option value="1"><fmt:message key="admin.panel.role.admin"/></option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" id="change_role_submit" class="btn btn-primary mb-3"><fmt:message
                                key="admin.panel.change"/></button>
                    </div>
                </form>
            </div>
            <div class="modal fade" id="change_role_modal" data-bs-target="#change_username_model" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><fmt:message key="lots-page.table.lot-status"/></h5>
                        </div>
                        <div class="modal-body">
                            <small class="success-change-role"><fmt:message
                                    key="admin.panel.role.success-role-change"/></small>
                            <small class="failure-change-role d-none"><fmt:message
                                    key="admin.panel.role.fail-role-change"/></small>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                    key="admin.panel.close"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
<script src="/static/js/ajax/panel.js"></script>
</html>
