
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
<html>
<head>
  <title><fmt:message key="main.title" /></title>
  <link rel="stylesheet" href="/css/bootstrap.css" type="text/css" />
  <link href="/css/main.css" rel="stylesheet" type="text/css" />

</head>
<body>
<form name="main" action="controller" method="get">
  <input type="hidden" name="command" value="main" />

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

  <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
      <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="/images/carousel/1c.jpg" alt="...">
        <div class="carousel-caption">
        </div>
      </div>
      <div class="item">
        <img src="/images/carousel/2c.jpg" alt="...">
        <div class="carousel-caption">
        </div>
      </div>
      <div class="item">
        <img src="/images/carousel/3c.jpg" alt="...">
        <div class="carousel-caption">
        </div>
      </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>

</form>

<script src="/jquery/jquery-2.1.3.min.js"></script>
<script src="/bootstrap/js/bootstrap_.js"></script>

</body>
</html>
</fmt:bundle>