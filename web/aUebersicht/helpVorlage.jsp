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
        <title>TITELSEITE</title>
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
                            <li><a href="#section1">THEMA 1</a></li>
                            <li><a href="#section2">THEMA 2</a></li>
                            <li><a href="#section3">THEMA 3</a></li>
                            <li><a href="#section4">THEMA 4</a></li>
                            <li><a href="#section5">THEMA 5</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>    

        
        <div id="section1"> <!--DECKBLATT-->
            <h1>THEMA 1</h1>
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <p></p>
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section2"> <!--THEMA 2-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section3"> <!--THEMA 3-->
            <div class="col-md-3"></div>
            <div class="col-md-6">
                
            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section4"> <!--THEMA 4-->
            <div class="col-md-3"></div>
            <div class="col-md-6">

            </div>
            <div class="col-md-3"></div>
        </div>

        <div id="section5"> <!--THEMA 5-->
            <div class="col-md-3"></div>
            <div class="col-md-6">

            </div>
            <div class="col-md-3"></div>
        </div>

    </body>
</html>
