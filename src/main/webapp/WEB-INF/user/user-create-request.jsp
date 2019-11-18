<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


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
<%--<a href="${pageContext.request.contextPath}/app/user/create_comment"> See my comment</a>--%>
<%--<a href="${pageContext.request.contextPath}/app/user/all_requests"> See my requests</a>--%>
<div  style="margin:0 auto;" class="row justify-content-center align-items-center align-content-center align-self-center">
    <input name="request" type="hidden"/>
    <div class="col" style="width: 404px;">
        <c:if test="${requestScope.sucess eq true}">
            <div class="alert alert-primary" align="center">
                <strong><fmt:message key="message.request.created"/></strong>
            </div>
        </c:if>
        <form  action="${pageContext.request.contextPath}/app/user/create_request"
               method="get">
            <label><fmt:message key="message.new.request"/></label>
            <br/>
            <textarea name="request" rows="3"></textarea>
            <br/>
            <button type="submit"  class="btn btn-light">
                <fmt:message key="message.create"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>