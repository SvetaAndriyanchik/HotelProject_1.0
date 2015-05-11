<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
<html>
<head>
    <title><fmt:message key="admin.title" /></title>
    <link href="/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1 class="h1"><fmt:message key="admin.h1"/></h1>
<table class="simple-little-table" cellspacing='0' style="
        margin: auto;">
  <tr>
    <td><fmt:message key="admin.table.id"/></td>
    <td><fmt:message key="admin.table.guests"/></td>
    <td><fmt:message key="admin.table.arrival"/></td>
    <td><fmt:message key="admin.table.departure"/></td>
    <td><fmt:message key="admin.table.login"/></td>
    <td> </td>
  </tr>
    <c:forEach items="${orders}" var="element">
    <tr>
      <td>${element.orderId}</td>
      <td>${element.guests}</td>
      <td>${element.arrivalDate}</td>
      <td>${element.departureDate}</td>
      <td>${element.login}</td>
      <td>
        <form name="orders" action="controller" method="post">
          <button type="submit" name="command" value = "check_order" class="button"><fmt:message key="admin.handle"/></button>
          <input type = "hidden" name = "guests" value = ${element.guests}>
          <input type = "hidden" name = "arrival" value = ${element.arrivalDate}>
          <input type = "hidden" name = "departure" value = ${element.departureDate}>
          <input type = "hidden" name = "login" value = ${element.login}>
          <input type = "hidden" name = "orderId" value = ${element.orderId}>
        </form>
      </td>

    </tr>
  </c:forEach>

</table>

<h1 class="h1"><fmt:message key="admin.old"/></h1>
<table class="simple-little-table" cellspacing='0' style="
        margin: auto;">
  <tr>
    <td><fmt:message key="admin.table.id"/></td>
    <td><fmt:message key="admin.table.guests"/></td>
    <td><fmt:message key="admin.table.arrival"/></td>
    <td><fmt:message key="admin.table.departure"/></td>
    <td><fmt:message key="admin.table.login"/></td>
  </tr>
  <c:forEach items="${old_orders}" var="element">
    <tr>
      <td>${element.orderId}</td>
      <td>${element.guests}</td>
      <td>${element.arrivalDate}</td>
      <td>${element.departureDate}</td>
      <td>${element.login}</td>
    </tr>
  </c:forEach>

</table>
<p>${errorMessage}</p>
</body>
</html>
</fmt:bundle>