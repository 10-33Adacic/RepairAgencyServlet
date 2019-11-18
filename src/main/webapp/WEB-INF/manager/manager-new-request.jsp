<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <title>Accepted requests</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

<nav class="navbar navbar-light navbar-expand-md navigation-clean-button">
    <div class="container"><a class="navbar-brand" href="#">RepAgency</a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse"
             id="navcol-1">
            <ul class="nav navbar-nav mr-auto">
                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/app/manager/new_requests">
                    <fmt:message key="message.all.requests"/>
                </a>
                </li>
                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/app/manager/all-comments">
                    <fmt:message key="message.all.comments"/>
                </a>
                </li>

            </ul>
            <span class="navbar-text actions"> <a class="login" href="${pageContext.request.contextPath}/app/logout" >
                 <fmt:message key="message.logout"/>
            </a></span>
            <a class="btn" id="locales"
               href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/static/United-Kingdom-flag-icon.png" height="30px"/></a>
            <a class="btn"
               href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/static/Ukraine-Flag-icon.png" height="30px"/> </a>

        </div>
    </div>
</nav>

<div style="margin-left:20px; margin-right: 20px;" class="row">
    <div class="col">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="message.request"/></th>
                    <th><fmt:message key="message.status"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${newRequests}" var="request">
                    <tr>
                        <td><c:out value="${request.request}"/></td>
                        <td><c:out value="${request.status}"/></td>

                        <td>
                            <a href="${pageContext.request.contextPath}/app/manager/new_requests/accept?id=${request.id}">
                                Accept
                            </a>
                            <a href="${pageContext.request.contextPath}/app/manager/new_requests/reject?id=${request.id}">
                                Reject
                            </a>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>


        </div>
    </div>
</div>
</form>
</body>
</html>
