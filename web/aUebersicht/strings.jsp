<%-- 
    Document   : aString
    Created on : 09.12.2015, 09:43:30
    Author     : wilmanm
--%>

<%@page import="framework.Aufgabe"%>
<%@page import="SQL.DbTools"%>
<%@page import="SQL.InBetween"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>String</title>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
        <script>
            function submitForm(elem) {
                document.getElementById("btnPressed").value = elem;
                document.frm2.submit();
            }
        </script>
        
        <%
            int kategorieID = 4;
            
            Connection con = DbTools.connect();
            Statement stmt = con.createStatement();
            
            ArrayList aufgabenliste= InBetween.aufgabenliste(kategorieID, stmt);
            ArrayList aufgabenIDliste = InBetween.aufgabenIDliste(kategorieID, stmt);
            String aufgabenuebersicht="";
            aufgabenuebersicht+= InBetween.aufgabenlisteHead();
            aufgabenuebersicht+=InBetween.aufgabelisteAusgabe(aufgabenliste,aufgabenIDliste,stmt);
            aufgabenuebersicht+=InBetween.aufgabenlisteFoot();
            %>
    </head>
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
