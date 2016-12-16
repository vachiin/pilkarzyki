<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Piłkarzyki 10 (epoch edition)</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
        body {
            background: linear-gradient(#f0f0f0, #909090);
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<form:form id="form_id" action="login.jsp" method="post">
    <div style="width: 100%; overflow: hidden;">
        <div style="border: 2px aqua; width: 200px; float: left;">
            login: <br/>
            <input title="login" type="text" name="cookie" />
        </div>
        <input type="submit" value="Zapisz" name="action"/>
    </div>


</form:form>
</body>
</html>