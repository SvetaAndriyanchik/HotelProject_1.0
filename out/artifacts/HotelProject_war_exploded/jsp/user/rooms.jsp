<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
<html>
<head>
    <link rel="stylesheet" href="../css/rooms.css" type="text/css" />
    <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
    <title><fmt:message key="rooms.title" /></title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="controller?command=goto_main"><span><fmt:message key="main.main" /></span></a></li>
                <li><a href="controller?command=goto_rooms" name="rooms"><span><fmt:message key="main.rooms" /></span></a></li>
                <li><a href="controller?command=goto_contacts" name="contacts"><span><fmt:message key="main.contacts" /></span></a></li>
                <li><a href="controller?command=goto_aboutus" name="aboutus"><span><fmt:message key="main.aboutus" /></span></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="controller?command=logout"><fmt:message key="main.logout" /></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

    <div class = "wrapper">
        <c:forEach items="${rooms}" var="element">
            <div class = element>
                <form name="rooms-form" action="controller" method="post">
                    <img class = roomlist-img src=${element.pictureURL}>
                    <div class="right-info">
                        <p></p>
                        <p><fmt:message key="rooms.guests"/> ${element.guests}</p>
                        <p><fmt:message key="rooms.price"/> ${element.price}</p>
                        <p><fmt:message key="rooms.description"/> ${element.description}</p>
                    </div>
                    <input type = "hidden" name = "guests" value = ${element.guests}>
                    <input type = "hidden" name = "price" value = ${element.price}>
                    <input type = "hidden" name = "picture" value = ${element.pictureURL}>

                    <button type="submit" name="command" value = "reserve" class="button"><fmt:message key="rooms.reserve"/></button>
                </form>
            </div>
        </c:forEach>
    </div>

</body>
</html>
</fmt:bundle>