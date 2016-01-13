<%-- 
    Document   : basicChapter
    Created on : 15.12.2015, 11:17:24
    Author     : palamito
--%>

<%@page import="SQL.DbTools"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Basics</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css"> 
        <link rel="stylesheet" href="css/hilfetexte.css"> 
        <script src="jquery/jquery-2.1.4.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>
    </head>

    <%
        String konsole = "";
        if (DbTools.connect() == null) {
            konsole = "<div class=\"alert alert-danger\" role=\"alert\">SERVER IST NICHT AN!</div>";
        }
    %>
        <!-- The navbar - The <a> elements are used to jump to a section in the scrollable area -->
    <body data-spy="scroll" data-target=".navbar" data-offset="50">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp">JavAcademy</a>
                </div>
                <div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href="#section1">Deckblatt</a></li>
                            <li><a href="#section2">Grundlagen</a></li>
                            <li><a href="#section3">Zusammenfassung</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>    

        <!-- Section 1 -->
        <div id="section1">
            <h1>Kapitel 1</h1>
            <p>Wilkommen bei JavAcademy,
                diese Webseite möchte dir auf einer einfachen 
                und freundlichen Weise programmieren beibringen.</p>
        </div>
        <div id="section2">
            <p>Wir fangen erstmal damit an dir zu zeigen 
                wie eine Klasse in Java aufgebaut ist.</p>
        </div>
        <div id="section3">
            <p>
            <img src="bilder/getting_started.png"/>
            </p>
        </div>

    </body>
</html>
