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
<div class="container-xxl">

    <div class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                    Изменить статус пользователя
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
                 data-bs-parent="#accordionFlushExample">
                <form class="row g-3 p-3" id="change_status_form">
                    <div class="col-auto">
                        <input type="text" name="username" pattern="^[a-zA-Z0-9_]{1,16}$" class="form-control"
                               id="change_status_username" placeholder="Username">
                    </div>
                    <div class="col-auto">
                        <select id="change_status_status" class="form-select" name="status">
                            <option selected value="1">Registered</option>
                            <option value="0">Banned</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" id="change_status_submit" class="btn btn-primary mb-3">Изменить</button>
                    </div>
                </form>
            </div>
            <div class="modal fade" id="change_status_modal" data-bs-target="#change_username_model" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                        </div>
                        <div class="modal-body">
                            <small class="success-change-status">Статус пользователя успешно изменен</small>
                            <small class="failure-change-status d-none">Ошибка! Проверьте правильность логина</small>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                    Изменить роль пользователя
                </button>
            </h2>
            <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo"
                 data-bs-parent="#accordionFlushExample">
                <form class="row g-3 p-3" id="change_role_form">
                    <div class="col-auto">
                        <input type="text" name="username" pattern="^[a-zA-Z0-9_]{1,16}$" class="form-control"
                               id="change_role_username" placeholder="Username">
                    </div>
                    <div class="col-auto">
                        <select id="change_role_role" class="form-select" name="status">
                            <option selected value="0">User</option>
                            <option value="1">Admin</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" id="change_role_submit" class="btn btn-primary mb-3">Изменить</button>
                    </div>
                </form>
            </div>
            <div class="modal fade" id="change_role_modal" data-bs-target="#change_username_model" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Role change</h5>
                        </div>
                        <div class="modal-body">
                            <small class="success-change-role">Роль пользователя успешно изменен</small>
                            <small class="failure-change-role d-none">Ошибка! Проверьте правильность логина</small>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                    Accordion Item #3
                </button>
            </h2>
            <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree"
                 data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                    richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck
                    quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid
                    single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore
                    wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings
                    occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of
                    them accusamus labore sustainable VHS.
                </div>
            </div>
        </div>
    </div>


    <%--    <form class="row g-3">--%>
    <%--        <div class="col-auto">--%>
    <%--            <input type="text" class="form-control" id="username" placeholder="Username">--%>
    <%--        </div>--%>
    <%--        <div class="col-auto">--%>
    <%--            <select id="inputState" class="form-select">--%>
    <%--                <option selected>Registered</option>--%>
    <%--                <option>BANNED</option>--%>
    <%--            </select>--%>
    <%--        </div>--%>
    <%--        <div class="col-auto">--%>
    <%--            <button type="submit" class="btn btn-primary mb-3">Confirm identity</button>--%>
    <%--        </div>--%>
    <%--    </form>--%>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
<script src="/static/js/ajax/panel.js"></script>
</html>
