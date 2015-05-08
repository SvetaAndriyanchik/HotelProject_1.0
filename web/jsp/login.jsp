<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
  <html><head>
    <title><fmt:message key="form.h1" /></title>
    <link href="/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
  </head>
  <body>
  <form name="login-form" action="controller" method="post">
    <button type="submit" name="language" value="Eng" class="lang-button"><img src="/images/gb.png" alt="eng"> </button>
    <button type="submit" name="language" value="Rus" class="lang-button"><img src="/images/russia.png" alt="ru"> </button>
  <div id="wrapper">
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
      <div class="login-form" >

      <div class="header">
        <h1><fmt:message key="form.h1" /></h1>
        <span><fmt:message key="form.span" /> </span>
      </div>

      <div class="content">

        <input name="username" type="text" class="input username" value=<fmt:message key="form.login" /> onfocus="this.value=''" />
        <input name="password" type="password" class="input password" value="password" onfocus="this.value=''" />
        <p></p>
        <p>${errorMessage}</p>
      </div>

      <div class="footer">
        <button type="submit" name="command" value=enter class="button"><fmt:message key="form.enter" /></button>
        <button type="submit" name="command" value=signup class="register"><fmt:message key="form.sign-up"  /></button>
      </div>
      </div>

  </div>
  <div class="gradient"></div>
  <script type="text/javascript" src="/js/login.js"></script>

  <input type="hidden" name="command" value="language" />
  </form>
  </body>
  </html>
</fmt:bundle>