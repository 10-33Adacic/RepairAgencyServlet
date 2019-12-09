<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="static/styles2.min.css">
</head>
<body>

<nav class="navbar navbar-dark bg-dark navbar-expand-md navigation-clean-button">
    <div class="container"><a class="navbar-brand" href="#"><fmt:message key="msg.repair.agency"/></a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse"
             id="navcol-1">

                <span class="navbar-text actions"> <a href="${pageContext.request.contextPath}/app/login" class="login"> <fmt:message key="message.login"/></a>
                    <a class="btn btn-light action-button" role="button" href="${pageContext.request.contextPath}/app/registration"
                       style="background-color: #0062cc;"> <fmt:message key="message.registration"/></a></a>
                </span>
                    <a class="btn" id="locales"
                       href="?sessionLocale=en"><img src="static/United-Kingdom-flag-icon.png" height="30px"/></a>
                <a class="btn"
                   href="?sessionLocale=ua"><img src="static/Ukraine-Flag-icon.png" height="30px"/> </a>


        </div>
    </div>
</nav>


<div data-bs-parallax-bg="true"
     style="height: 500px;background-image: url(static/background.jpg);background-position: center;background-size: cover;"></div>

<div class="footer-dark">
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-3 item">
                    <h3><a><fmt:message key="msg.services"/></a></h3>
                    <ul>
                        <li><a href="#"><fmt:message key="msg.web.design"/></a></li>
                        <li><a href="#"><fmt:message key="msg.development"/></a></li>
                        <li><a href="#"><fmt:message key="msg.hosting"/></a></li>
                    </ul>
                </div>
                <div class="col-sm-6 col-md-3 item">
                    <h3><a><fmt:message key="msg.about"/></a></h3>
                    <ul>
                        <li><a href="#"><fmt:message key="msg.company"/></a></li>
                        <li><a href="#"><fmt:message key="msg.team"/></a></li>
                        <li><a href="#"><fmt:message key="msg.careers"/></a></li>
                    </ul>
                </div>
                <div class="col-md-6 item text">
                    <h3><strong><a><fmt:message key="msg.repair.agency"/></a></strong><br></h3>
                    <p><br><a><fmt:message key="msg.welcome.meaage"/></a><br><br></p>
                </div>
                <!--<div class="col item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-instagram"></i></a></div>-->
            </div>
            <p class="copyright">Sadovskyi Maksym © 2019</p>
        </div>
    </footer>
</div>
</body>
</html>
