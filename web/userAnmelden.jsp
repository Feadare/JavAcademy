<%-- 
    Document   : userAnmelden.jsp
    Created on : 04.12.2015, 08:39:58
    Author     : feadare
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="framework.Benutzer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erfolgreiche Anmeldung!</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <%
        String konsole = "";

        konsole = (String) request.getAttribute("konsole");

   //String navbar = VorlageHTML.getNavbar((Boolean) session.getAttribute("eingeloggt"));
    %>
    <jsp:include page="navbar.jsp"></jsp:include> 
        <div class="col-md-3">

        </div>

        <div class="col-md-6">
            <br>
            <%=konsole%>
        </div>

        <div class="col-md-3">

        </div>

        
    </body>
</html>
