<%-- 
    Document   : userAnmelden.jsp
    Created on : 04.12.2015, 08:39:58
    Author     : feadare
--%>

<%@page import="framework.Hilfsmethoden"%>
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
        <title>Anmeldung!</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">

    </head>
    <%
        String konsole = "";
        String username = request.getParameter("login");
        String passwort = request.getParameter("password");
        
            Connection con = DbTools.connect();
            Statement stmt = con.createStatement();
        

        if (Benutzer.checkPassword(username, passwort.toCharArray(), con)) {
            konsole = "<div class=\"alert alert-success\" role=\"alert\"> "
                    + "Erfolgreich eingeloggt!</div>";
            
            int u_ID = Benutzer.getIDbyName(username, con);
            int exp = Benutzer.getUserExp(stmt, u_ID);
            String progress = Hilfsmethoden.progressbar(exp);
            session.setAttribute("ID",u_ID);
            session.setAttribute("username", username);
            session.setAttribute("eingeloggt", true);
            session.setAttribute("exp", exp);
            session.setAttribute("level", Benutzer.getUserLevel(stmt, u_ID));
            session.setAttribute("progress", progress);
        } else {
            konsole = "<div class=\"alert alert-danger\" role=\"alert\">Login fehlgeschlagen"
                    + "Username oder Passwort falsch?" + "</div>";
        }
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
