<%-- 
    Document   : aArrays
    Created on : 09.12.2015, 10:17:04
    Author     : wilmanm
--%>

<%@page import="SQL.DbTools"%>
<%@page import="SQL.InBetween"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arrays</title>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
        <script>
            function submitForm(elem) {
                document.getElementById("btnPressed").value = elem;
                document.frm2.submit();
            }
        </script>
    </head>
    <%
        int kategorieID = 7;
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();

        ArrayList aufgabenliste = InBetween.aufgabenliste(kategorieID, stmt);
        ArrayList aufgabenIDliste = InBetween.aufgabenIDliste(kategorieID, stmt);
        String aufgabenuebersicht = "";
        aufgabenuebersicht += InBetween.aufgabenlisteHead();
        aufgabenuebersicht += InBetween.aufgabelisteAusgabe(aufgabenliste, aufgabenIDliste,stmt);
        aufgabenuebersicht += InBetween.aufgabenlisteFoot();
    %>
    <body>
        <jsp:include page="../navbar.jsp"></jsp:include>
            <div class="col-md-4">

            </div>
            <div id="hilfe" class="col-md-5">
            <%=aufgabenuebersicht%>
        </div>

        <div class="col-md-4">

        </div>
    </body>
</html>
