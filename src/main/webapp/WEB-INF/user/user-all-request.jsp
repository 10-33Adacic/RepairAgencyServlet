<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>



<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Create request</title>

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
                                        href="${pageContext.request.contextPath}/app/user/create_request">
                    <fmt:message key="message.new.request"/>
                </a>
                </li>
                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/app/user/all_requests">
                    <fmt:message key="message.my.requests"/>
                </a>
                </li>

                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/app/user/create_comment">
                    <fmt:message key="message.new.comment"/>
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

<div style="margin-top: 15px" class="container">
    <h2>
        <fmt:message key="message.my.requests"/>
    </h2>
    <form method="get">

            <table class="table table-hover">
                <thead>
                <tr>

                    <th><fmt:message key="message.request"/></th>
                    <th><fmt:message key="message.status"/></th>
                    <th><fmt:message key="message.price"/></th>
                    <th><fmt:message key="message.reason"/></th>
                </tr>
                </thead>
                <c:forEach items="${requests}" var="r">
                    <tbody>
                    <tr>
                        <td><c:out value="${r.request}"/></td>
                        <td><c:out value="${r.status}"/></td>
                        <td><c:out value="${r.price}"/></td>
                        <td><c:out value="${r.reason}"/></td>

                    </tr>
                    </tbody></c:forEach>
            </table>

    </form>
</div>

<div style="margin-top: 15px" class="container">
    <h2>
        <fmt:message key="message.reject.requests"/>
    </h2>
    <form method="get">

        <table class="table table-hover">
            <thead>
            <tr>

                <th><fmt:message key="message.request"/></th>
                <th><fmt:message key="message.status"/></th>
                <th><fmt:message key="message.price"/></th>
<%--                <th><fmt:message key="message.reason"/></th>--%>
            </tr>
            </thead>
            <c:forEach items="${rejectRequests}" var="rr">
                <tbody>
                <tr>
                    <td><c:out value="${rr.request}"/></td>
                    <td><c:out value="${rr.status}"/></td>
<%--                    <td><c:out value="${rr.price}"/></td>--%>
                    <td><c:out value="${rr.reason}"/></td>

                </tr>
                </tbody></c:forEach>
        </table>

    </form>
</div>
<div class="col-sm-12 col-md-7">
    <%--            <c:if test="${elementsCount > size}">--%>
    <div class="dataTables_paginate paging_simple_numbers text-right">
        <ul class="pagination">

            <c:forEach begin="1" end="${pagesCount}" var="i">
                <li class="paginate_button page-item ${page == i ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/app/user/all_requests?page=${i}" class="page-link">${i}</a>
                </li>
            </c:forEach>

        </ul>
    </div>
    <%--            </c:if>--%>
</div>
</body>
</html>