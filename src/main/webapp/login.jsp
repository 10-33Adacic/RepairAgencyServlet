<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Login Page</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<a class="btn" id="locales"
   href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/static/United-Kingdom-flag-icon.png" height="30px"/></a>
<a class="btn"
   href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/static/Ukraine-Flag-icon.png" height="30px"/> </a>
<div class="login-form" align="center">

<h1><fmt:message key="message.login"/></h1><br/>

    <c:if test="${requestScope.error eq true}">
        <div class="alert alert-danger" align="center">
            <strong>User with this email is already logged or invalid password</strong>
        </div>
    </c:if>
<form method="post" action="${pageContext.request.contextPath}/app/login">

    <div class="form-group">
        <div class="input-group">
<%--        <span class="input-group-addon"><i class="fa fa-user"></i></span>--%>
            <input type="text" name="email"  placeholder=  <fmt:message key="message.email"/>><br/>
        </div>


    <div class="form-group">
        <div class="input-group" align="center">
<%--            <span class="input-group-addon"><i class="fa fa-lock" align="center"></i></span>--%>
            <input type="password"   name="pass" placeholder= <fmt:message key="message.password"/>><br/>
        </div>
    </div><br/>
    <div class="form-group">
    <button class="button" type="submit" class="btn btn-primary login-btn btn-block"> <fmt:message key="message.login"/>
    </button>
    </div>
    <p class="message"><a href="${pageContext.request.contextPath}/app/registration"> <fmt:message key="message.create.account"/></a>
    </p>
    </div>
</form>
</div>
<br/>



</body>
</html>