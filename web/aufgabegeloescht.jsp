<%-- 
    Document   : aufgabegeloescht.jsp
    Created on : 15.01.2016, 08:39:58
    Author     : feadare
--%>

<%@page import="SQL.DbTools"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="framework.Benutzer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erfolgreiche Löschung!</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <%
        String konsole = "";
        int aufgabenId = Integer.parseInt(request.getParameter("aufgabenID"));
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();
        String sql1 = "DELETE FROM TESTS WHERE AUFGABENID = " + aufgabenId;
        String sql2 = "DELETE FROM TESTPARAM WHERE AUFGABENID = " + aufgabenId;
        String sql3 = "DELETE FROM EINGABEN WHERE AUFGABENID = " + aufgabenId;
        String sql4 = "DELETE FROM METHODENPARAMETER WHERE AUFGABENID = " +aufgabenId;
        String sql5 = "DELETE FROM AUFGABEN WHERE ID = " +aufgabenId;
        konsole += stmt.executeUpdate(sql1);
        konsole += stmt.executeUpdate(sql2);
        konsole += stmt.executeUpdate(sql3);
        konsole += stmt.executeUpdate(sql4);
        konsole += stmt.executeUpdate(sql5);
   //String navbar = VorlageHTML.getNavbar((Boolean) session.getAttribute("eingeloggt"));
    %>
    <jsp:include page="navbar.jsp"></jsp:include> 
        <div class="col-md-3">

        </div>

        <div class="col-md-6">
            <br>
            <div class="alert alert-info" role="farewell">Aufgabe wurde entfernt.</div>
        </div>

        <div class="col-md-3">

        </div>

        
    </body>
</html>
