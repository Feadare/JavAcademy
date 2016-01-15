<%-- 
    Document   : logout
    Created on : 10.12.2015, 08:34:24
    Author     : wilmanm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <%         
            response.setContentType("text/html;charset=UTF-8");
            session.invalidate();
        %>
    </head>
    <jsp:include page="navbar.jsp"></jsp:include>


    <body>
        <div class="alert alert-info" role="farewell">'Sch&uuml;ss! Bis zum n&auml;chsten mal!</div>
    </body>
</html>
