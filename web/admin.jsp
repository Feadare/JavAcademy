<%-- 
    Document   : admin
    Created on : 13.01.2016, 09:35:56
    Author     : feadare
--%>

<%@page import="SQL.DbTools"%>
<%@page import="SQL.InBetween"%>
<%@page import="framework.Benutzer"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <%
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();

        int id = Benutzer.getIDbyName((String) session.getAttribute("username"),con);
        String profile = InBetween.myProfile(stmt, id);

    %>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="col-md-3">

        </div>

        <div class="col-md-6">
            <%=profile%>
            <a class="btn btn-primary" href="profilbearbeiten.jsp">Profil bearbeiten</a>
        </div>
            <a class="btn btn-primary" href="download.jsp">Datenbank-Backup runterladen</a>

        <div class="col-md-3">

        </div>
        
    </body>
</html>
