<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
    <html><head>
        <title></title>
        <link href="/css/signup.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
    <div id="wrapper">

        <form name="login-form" class="login-form" action="controller" method="post">

            <div class="header">
                <h1><fmt:message key="signup.form.h1" /></h1>
            </div>

            <div class="content">
                <label for="login" class="label"><fmt:message key="signup.form.login" /></label>
                    <input required name="login" id="login" type="text" class="input"/>
            </div>
            <div class="content">
                <label for="password" class="label"><fmt:message key="signup.form.password" /></label>
                    <input required name="password" id="password" type="password" class="input"/>
            </div>
            <div class="content">
                <label for="email" class="label"><fmt:message key="signup.form.email" /></label>
                    <input required name="email" id="email" type="email" class="input"/>
            </div>
            <div class="content">
                <label for="account" class="label"><fmt:message key="signup.form.account" /></label>
                    <input required name="account" id="account" type="text" class="input"/>
                <p>${errorInsertMessage}</p>
            </div>

            <div class="footer">
                <button type="submit" name="command" value=ok_signup class="button">OK</button>
            </div>

        </form>

    </div>
    <div class="gradient"></div>
    </body></html>
</fmt:bundle>