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

<form style="padding-top:75px" method="post" id="formAccept" action="${pageContext.request.contextPath}/app/manager/new_requests/accept/done">
    <label> <fmt:message key="message.price"/></label>
    <input style="margin-left:20px; margin-right: 20px;"
          name="price"
           type="text"
           class="form-control"
           pattern="[0-9]+"/>
    <input value="${id}" name ="id" type="hidden"/>

    <label><fmt:message key="message.master"/></label>
    <select name="email" >
<c:forEach items="${masters}" var="master">
        <option value="${master.email}">
            <c:out value="${master.email}"/>
        </option>
</c:forEach>
    </select>

    <button type="submit"><fmt:message key="message.accept"/></button>
</form>
</body>
</html>
