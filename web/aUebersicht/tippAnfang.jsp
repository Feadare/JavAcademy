<%-- 
    Document   : basicChapter
    Created on : 15.12.2015, 11:17:24
    Author     : palamito
--%>

<%@page import="framework.Hilfsmethoden"%>
<%@page import="SQL.DbTools"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FAQ / Generelle Tipps</title>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.css"> 
        <link rel="stylesheet" href="../css/hilfetexte.css"> 
        <script src="../jquery/jquery-2.1.4.js"></script>
        <script src="../bootstrap/js/bootstrap.js"></script>
    </head>
    <%
        String comFehler = Hilfsmethoden.helpCompiler();
        String datentypen = Hilfsmethoden.helpDatentypen();
        String schleifen = Hilfsmethoden.helpSchleifen();
    %>
    <!-- The navbar - The <a> elements are used to jump to a section in the scrollable area -->
    <body data-spy="scroll" data-target=".navbar" data-offset="50">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp">JavAcademy</a>
                </div>
                <div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href="#section1">Compilerfehler </a></li>
                            <li><a href="#section2">Begriffe</a></li>
                            <li><a href="#section3">Beispielklasse</a></li>
                            <li><a href="#section4">Goldene Regeln</a></li>
                            <li><a href="#section5">Schleifen</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>    

        
        <div id="section1"> <!--Compilerfehler-->
            <h1>Typische Compilerfehler</h1>
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <p><%=comFehler%></p>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section2"> <!--Begriffe-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <p>Klasse - Name einer Sammlung von Methoden/Aufgabe (z.B: Lagerraum)</p>
                <p>Methode - Ein Aufgabe ( z.B einlagern() )</p>
                Datentypen: <p><%=datentypen%></p>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section3"> <!--Beispielklasse-->
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <p>Wie schreibt man eine Klasse?</p>
                <img src="../bilder/getting_started.png"/>
            </div>
            <div class="col-md-1"></div>
        </div>

        <div id="section4"> <!--Goldene Regeln-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <ol type="I">
                    <li>NUR Klassennamen werden grossgeschrieben</li>
                    <li>Attribute werden IMMER private gesetzt</li>
                    <li>(Auto-)formatierung nutzen für Lesbarkeit!</li>
                    <li>“If it can be null, it will be null” :P</li>
                    <li>KISS - Keep It Short and Simple</li>
                    <li>Empfehle uns weiter!</li>
                </ol>

            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section5"> <!--Schleifen-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <%=schleifen%>

                Ausformoliert:<br><br>

                Variable 'i' wird mit 0 initialisiert.(int i = 0)<br>
                Solange i kleiner oder gleich 10 ist (<=10) wird die Anweisung ausgef&uuml;rt.<br>
                Am Ende wird i um eins hochgezählt (i++).
                <br><br>
                <b>while( code.nextLine() ) {..System.out.print...}</b><br>
                Solange der Text 'code' noch eine Zeile hat wird etwas gemacht.<br><br>
            </div>
            <div class="col-md-3"></div>
        </div>

    </body>
</html>
