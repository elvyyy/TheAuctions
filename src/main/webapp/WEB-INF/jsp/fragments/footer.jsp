<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 1/12/21
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${ not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale }" scope="session"/>
<fmt:setBundle basename="message"/>
<footer class="site-footer">
    <div class="container-xxl">
        <div class="row">
            <div class="col-sm-12 col-md-6">
                <h6><fmt:message key="footer.label.about"/></h6>
                <p class="text-justify"><fmt:message key="footer.label.about.description" /></p>
            </div>

            <div class="col-xs-6 col-md-3">
                <h6><fmt:message key="footer.label.languages" /></h6>
                <ul class="footer-links">
                    <li class="footer-lang-link" onclick="changeLanguage('en_US'); return false;"><fmt:message key="footer.label.languages.en"/></li>
                    <li class="footer-lang-link" onclick="changeLanguage('ru_RU'); return false;"><fmt:message key="footer.label.languages.ru"/></li>
                </ul>
            </div>
            <div class="col-xs-6 col-md-3">
                <h6><fmt:message key="footer.label.links" /></h6>
                <ul class="footer-links">
                    <li><a href="/"><fmt:message key="footer.label.links.home" /></a></li>
                    <li><a href="javascript:void(0)"><fmt:message key="footer.label.links.about-us" /></a></li>
                    <li><a href="tel:+375441111111"><fmt:message key="footer.label.links.contact-us" /></a></li>
                    <li><a href="javascript:void(0)"><fmt:message key="footer.label.links.privacy" /></a></li>
                </ul>
            </div>
        </div>
        <hr>
    </div>
    <div class="container-xxl">
        <div class="row">
            <div class="col-md-8 col-sm-6 col-xs-12">
                <jsp:useBean id="date" class="java.util.Date"/>
                <p class="copyright-text"><fmt:message key="footer.copyright"/> &copy; <fmt:formatDate value="${date}" pattern="yyyy"/> <fmt:message key="footer.copyright.rights"/>
                    <a href="/"><fmt:message key="main.name"/></a>.
                </p>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <ul class="social-icons">
                    <li><a class="facebook" href="javascript:void(0)"><i class="fa fa-facebook"></i></a></li>
                    <li><a class="twitter" href="javascript:void(0)"><i class="fa fa-twitter"></i></a></li>
                    <li><a class="dribbble" href="javascript:void(0)"><i class="fa fa-dribbble"></i></a></li>
                    <li><a class="linkedin" href="javascript:void(0)"><i class="fa fa-linkedin"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
<script src="https://use.fontawesome.com/6286fc387a.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/static/js/ajax/changelanguage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>