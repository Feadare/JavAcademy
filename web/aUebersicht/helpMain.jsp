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
        <title>Hilfe: Mainklasse</title>
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
                            <li><a href="#section3">Main-Methode</a></li>
                            <li><a href="#section41">Alter ändern</a></li>
                            <li><a href="#section42">Alter ausgeben</a></li>
                            <li><a href="#section5">Merken!</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>    


        <div id="section1"> <!--DECKBLATT-->
            <h1>Startklasse/Mainklasse</h1>
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <p>von Maxim Wilman</p>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section2"> <!--Einleitung-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                Hey! Willkommen zurueck USER <br>
                Heute beschäftigen wir uns mit dem Thema "Startklassen".
                <br><br><br>
                Was ist das und warum brauchen wir das überhaupt?<br>
                Okay, stell dir mal vor du hast tolle Attribute, schöne elegante Methoden.<br>
                Wenn du brav unser Tutorial befolgt hast bin ich mir sicher dass du das erreichen kannst ;)<br>
                Und jetzt stell dir vor du willst einfach mal auf den Start-Knopf drücken.. Was soll das passieren?<br><br>
                <b>Wie wäre es mit einer coolen Textausgabe?</b><br>
                Genau dafür programmieren wir eine <b>Startklasse</b>.<br>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section3"> <!--Main-Methode-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                Die Starklasse hat eine bestimmte Methode, 'main'-Methode genannt und sieht so aus:<br>
                <img src="../bilder/MainCode.PNG"<br>
                Schauen wir uns das mal genauer an:<br><br>

                Eine public Methode welche eine Argumentenliste aufnimmt <br>
                Das könnte man vergleichen, als wenn du ein Spiel im Fenstermodus starten willst.<br>
                Viele Spiele lassen sich als Startparameter '-windowed' anhängen lassen.<br>
                Aber für uns erstmal irrelevant da wir das gar nicht verwenden fürs erste.<br><br>
                Wie können wir jetzt unsere Klassen und Objekte verwenden?<br>
                Ich zeigs dir an meinem <a href="helpObjekte.jsp">'Person' - Beispiel</a>(Klick) aus einem anderen Thema
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section41"> <!--THEMA 4-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <img src="../bilder/PersonCode.PNG" alt=""/><br>
                Hier nochmal die Personen Klasse.<br>

                Wir können jetzt, wie im letzten Thema angedeutet, beispielsweise drei Objekte erstellen.<br>
                <img src="../bilder/PersonObjekte.PNG" alt=""/><br>
                Ralph, Walter und Kurt.<br>
                <br>
                Wir können auch mehr Objekte erstellen und je nachdem welchen Konstruktor wir wählen die Attribute vergeben.<br>
                <br>
                Lass mich das genauer erklären:<br>
                p1, p2 und p3 sind alles Objekte von der Klasse 'Person'.<br>
                Ich kann alle public Methoden hier verwenden, also alle getter und setter Methoden beispielsweise hier.
                <br>
                Okay, lasst uns jetzt mal ein Objekt verändern, beispielsweise das Alter vom Ralph.<br>
                Wir nutzen also die Methode 'setAlter(int alter)' und überschreiben das Alter von p1.<br><br>
                <b>p1.setAlter(18);</b><br>
                'p1' wird praktisch angesprochen und soll die Methode 'setAlter' mit Parameter '18' verwenden.<br>
                Das überschreibt das Alter mit dem neuen übergebenen Alter '18'. Alter sehr viele alters. genauso altmodisch ist auch der Witz.<br>
                <img src="../bilder/PersonSetAlter.PNG" alt=""/>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section4"> <!--THEMA 4-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                Alles klar!<br>
                Aber wie schauen wir jetzt ob sich etwas verändert hat?<br>
                Easy: <b>System.out.println</b><br>
                Falls du das nicht kennst, kurz gesagt: Es gibt einen Text in der Konsole aus als neue Zeile.<br>
                Wir wollen das Alter zurück geben, also nutzen wir die Methode 'getAlter'
                <img src="../bilder/PersonGetAlter.PNG" alt=""/>
                Und folgendes kommt in der Konsole raus:<br>
                <img src="../bilder/PersonOutAlter.PNG" alt=""/><br>
                In der Konsole steht eine 18.<br>
                Schmücken wir mal den print Befehl etwas aus:
                System.out.println("Alter von p1: "+p1.getAlter());<br>
                Und schon steht in der Konsole<br>
                <b>Alter von p1: 18</b>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section5"> <!--Merken-->
            <div class="col-md-3"></div>
            <div class="col-md-6"><form action="helpMain.jsp#section3">
                </form>
                Wir merken uns: Wie schreibt man den Kopf einer Startmethode?<br>
                public static void <input type="text" name="main" value="" size="10" />(String[] args) {<br>
                <br>
                }<br>
                <input type="submit" value="Check!" />
            </div>
            <div class="col-md-3"></div>
        </div>

    </body>
</html>
