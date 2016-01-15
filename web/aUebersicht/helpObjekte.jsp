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
        <title>Hilfe: Objekte</title>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.css"> 
        <link rel="stylesheet" href="../css/hilfetexte.css"> 
        <script src="../jquery/jquery-2.1.4.js"></script>
        <script src="../bootstrap/js/bootstrap.js"></script>
    </head>
    <%
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
                            <li><a href="#section1">Startseite</a></li>
                            <li><a href="#section2">Einleitung</a></li>
                            <li><a href="#section3">Beispiel: Person</a></li>
                            <li><a href="#section4">Personen-objekte</a></li>
                            <li><a href="#section51">Lückentext</a></li>
                            <li><a href="#section52">Lösung</a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </nav>    


        <div id="section1"> <!--DECKBLATT-->
            <h1>Thema: Objekte</h1>
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <p>von Maxim Wilman</p>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section2"> <!--Einleitung-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                Hey, ich werde dich hier durch das Thema 'Objekte' führen.<br>
                Bestimmt hast du den Begriff <b>"OBJEKTorientierte Programmiersprache"</b> gehört.<br>
                Aber was heißt das eigentlich?<br>
                <br><br>
                In Java kann man Objekte einer Klasse machen.<br>
                Jedes dieser Objekte kann man Werte für Attribute/Eigenschaften geben.<br>
                Genauer gesagt: Der <b>Konstruktor</b> fragt nach Werten. <br>
                <br>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section3"> <!--Beispiel: Person-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <p>Beispiel: Klasse Person</p>
                <img src="../bilder/PersonUML.PNG"/>
                <img src="../bilder/PersonCode.PNG" alt=""/>
                <br><br>
                Wir sehen hier die Klasse Person<br>

                Wenn wir jetzt den ersten Konstruktor nutzen, welcher keine Paramater besitzt,
                Werden Standardwerte eingesetzt, welche wir angegeben haben.<br><br>

                Im zweiten Konstruktor können wir einen Vornamen und ein Alter angeben.<br>
                ACHTUNG: Für die anderen werden NULL werte eingeben! Entweder set-Methoden oder Werte vorgeben!
                <br><br>

                Im dritten Konstrukor geben wir alle möglichen Attribute an.

            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section4"> <!--Personen-objekte-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                So.. Schauen wir uns dochmal an wie solche Objekte aussehen können.<br>
                <img src="../bilder/PersonObjekte.PNG" alt=""/><br><br>
                &COPY;BlueJ<br>
                Yeah! Wir haben soeben drei einzigartige Personen programmiert.<br>
                Aber was ist mit Methoden?<br>
                Natürlich kann jedes Objekt auch die Methoden seiner Klasse benutzen.<br>
                Lasst uns doch gleich eine Methode hinzufügen!

            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section51"> <!--Lückentext-Aufgabe-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                Machen wir eine getter-Methode für den Vornamen!<br><br>
                public String getNachname(){<br>
                return nachname;<br>
                }<br><br><br>

                Jetzt du! Mach eine getter-Methode für das Alter!
                <form action="helpObjekte.jsp#section52" method="POST">
                    public <input type="text" name="int" value="" size="10" /> getAlter(){<br>
                    <input type="text" name="return" value="" size="10" />
                    <input type="text" name="alter" value="" size="10" />;<br>
                    }<br><input type="submit" value="Check!" />
                    <br><br>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section52"> <!--Lückentextlösung-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h3>Lösung</h3>
                Jetzt du! Mach eine getter-Methode für das Alter!<br><br>
                public int getAlter(){<br>
                return alter;<br>
                }<br><br><br>
                Gut! Aber zurück zu den Personenobjekten.<br>
                Wie rufen wir denn jetzt das Alter ab vom Walter?<br>
                Dazu brauchen wir eine "main-methode", das lernst du hier:
                <a href="helpMain.jsp">Main-Methode</a><br><br>
            </div>
            <div class="col-md-3"></div>
        </div>

    </body>
</html>
