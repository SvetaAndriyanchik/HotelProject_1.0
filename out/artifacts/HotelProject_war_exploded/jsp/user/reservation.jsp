<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" >
<html>
<head>
    <title><fmt:message key="reservation.title" /></title>
    <link rel="stylesheet" href="/css/reservation.css" type="text/css" />
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

</head>
<body>

<form name="reservation" action="controller" method="post">

    <input name = "room_price" type="hidden" value=${room_price}>
        <div class = wrapper>
            <div class = reservation-form>
                <div class = header>

                    <div class="container">
                        <div class="form-horizontal">
                            <fieldset>
                                <div class="control-group" style="margin-top: 50px;">
                                    <label class="control-label"><fmt:message key="reservation.arrival" /></label>
                                    <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                        <input required name = arrival class = "input" size="16" type="text" value="" readonly>
                                        <span class="add-on"><i class="icon-remove"></i></span>
                                        <span class="add-on"><i class="icon-th"></i></span>
                                    </div>
                                    <input type="hidden" id="dtp_input2" value="" /><br/>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                    <div class="container">
                        <div class="form-horizontal">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label"><fmt:message key="reservation.departure" /></label>
                                    <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                        <input required name = departure class = "input" size="16" type="text" value="" readonly>
                                        <span class="add-on"><i class="icon-remove"></i></span>
                                        <span class="add-on"><i class="icon-th"></i></span>
                                    </div>
                                    <input type="hidden" id="dtp_input3" value="" /><br/>
                                </div>
                            </fieldset>
                        </div>
                    </div>


                    <div class="container">
                        <div class="form-horizontal">
                            <div class="control-group">
                                <label for="guests" class = "control-label"><fmt:message key="reservation.guests" /></label>
                                <input required name = guests id = "guests" class = "docked-inpt" size="3" type="text" value=${guests_number} readonly>
                            </div>
                        </div>
                    </div>

                    <img class = room-img src="${pictureURL}">
                    <p>${errorMessage}</p>
                </div>
                <div class = footer>
                    <button type="submit" name="command" value=ok_reserve class="button">OK</button>
                </div>
            </div>
        </div>
</form>
<script type="text/javascript" src="/jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language:  'en',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language:  'en',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
</body>
</html>
</fmt:bundle>