<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="/css/order_.css" type="text/css" />
</head>
<body>
  <div class="wrapper">
      <div class = inside>
          <label for="select" class = h1><fmt:message key="admin.rooms"/></label>
          <form name="order" action="controller" method="post">
              <input type = "hidden" name = "orderId" value = ${orderId}>
            <select name = "room" id = "select">
              <c:forEach items="${rooms}" var="element">
                <option value = "${element.roomNumber}">${element.roomNumber}</option>
              </c:forEach>
            </select>
            <button type="submit" name="command" value = "send_answer" class="button">OK</button>
          </form>
      </div>

    <p>${message}</p>
  </div>

</body>
</html>
</fmt:bundle>