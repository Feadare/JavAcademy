<%-- 
    Document   : uebersicht
    Created on : 03.12.2015, 08:31:43
    Author     : wilmanm
--%>

<%@page import="SQL.DbTools"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aufgaben&uuml;bersicht</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>

    <%
        String konsole = "";
        if (DbTools.connect() == null) {
            konsole = "<div class=\"alert alert-danger\" role=\"alert\">SERVER IST NICHT AN!</div>";
        }
    %>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>



        <center><h1>Aufgaben&uuml;bersicht</h1></center>
        <div class="col-md-3" id="hilfe">
            <ul class="list-group">
                <a class="list-group-item" href="basicChapter.jsp">Erste Hilfe ;-)</a>
                <a class="list-group-item" href="aUebersicht/tippAnfang.jsp">Probleme? Nachschlagwerkzeug</a>
                <a class="list-group-item" href="aUebersicht/helpObjekte.jsp">Hilfe zu: Objekten</a>
                <a class="list-group-item" href="aUebersicht/helpMain.jsp">Hilfe zu: Startklasse/Mainklasse</a>
            </ul>
        </div>
        <div class="col-md-3" id="aufgaben">
            <ul class="list-group">
                <a class="list-group-item" href="aUebersicht/tippAnfang.jsp">Tipps zu Anfang</a>
                <a class="list-group-item" href="aUebersicht/basics.jsp">Basics</a>
                <a class="list-group-item" href="aUebersicht/fallunterscheidungen.jsp">Fallunterscheidungen</a>
                <a class="list-group-item" href="aUebersicht/schleifen.jsp">Schleifen</a>
                <a class="list-group-item" href="aUebersicht/strings.jsp">String</a>
                <a class="list-group-item" href="aUebersicht/logik.jsp">Logik</a>
                <a class="list-group-item" href="aUebersicht/zahlen.jsp">Zahlen</a>
                <a class="list-group-item" href="aUebersicht/arrays.jsp">Arrays</a>
                <a class="list-group-item" href="aUebersicht/felder.jsp">Felder</a>
                <a class="list-group-item" href="aUebersicht/fortgeschrittene.jsp">Fortgeschrittene</a>
                <a class="list-group-item" href="aUebersicht/andere.jsp">Andere</a>
            </ul>
        </div>

        <div class="col-md-3" id="sonstige">

            <ul class="list-group">
                <a class="list-group-item" href="neuerUser.jsp">Neuer User</a>
                <a class="list-group-item" href="neueAufgabe.jsp">Neue Aufgabe hinzuf&uuml;gen</a>
            </ul>
        </div>
    <%=konsole%>
</body>
</html>
