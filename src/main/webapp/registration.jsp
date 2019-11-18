<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head lang="${sessionScope.lang}">
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Registration</title>
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
<%--        <h2>--%>
<%--            This is registration form! <br/>--%>
<%--        </h2>--%>
<div class="login-form" align="center">


    <c:if test="${requestScope.error eq true}">
        <div class="alert alert-danger" align="center">
            <strong>User with this email already exists</strong>
        </div>
    </c:if>
        <form method="post" action="${pageContext.request.contextPath}/app/registration">

            <div class="form-group">
                <div class="input-group">
<%--                    <span class="input-group-addon"><i class="fa fa-user"></i></span>--%>
                    <input type="text" name="name" placeholder=<fmt:message key="message.name"/>><br/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
<%--                    <span class="input-group-addon"><i class="fa fa-inbox"></i></span>--%>
                    <input type="text"  name="email" placeholder=<fmt:message key="message.email"/>><br/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
<%--                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>--%>
                    <input type="password"  name="pass" placeholder=<fmt:message key="message.password"/>><br/>
                </div>
            </div>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label>
                    <fmt:message key="message.select.role"/>
                </label>
                <select name="role" path="roles" class="selectpicker">
                    <option value="ROLE_USER">
                        ROLE_USER
                    </option >
                    <option  value="ROLE_MASTER">
                        ROLE_MASTER
                    </option >
                    <option  value="ROLE_MANAGER">
                        ROLE_MANAGER
                    </option >
                </select>
            </div><br/>

<%--            <span class="red" >--%>
<%--                <c:if test="not empty inwalidInput">--%>
<%--            <c:out value=" ${inwalidInput}"/>--%>
<%--                </c:if>--%>
<%--            </span>--%>
            <div class="form-group">
                <button class="btn btn-primary login-btn btn-block" type="submit" value="Submit">
                    <fmt:message key="message.registration"/>
                </button>
            </div>
            <p class="message"><a href="${pageContext.request.contextPath}/index.jsp"> <fmt:message key="message.sign.in"/></a>
            </p>
        </form>
</div>
</body>
</html>