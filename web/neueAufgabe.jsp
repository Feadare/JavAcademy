<%-- 
    Document   : neueAufgabe
    Created on : 04.12.2015, 09:44:44
    Author     : wilmanm
--%>

<%@page import="java.sql.Connection"%>
<%@page import="SQL.DbTools"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css"> 
        <link rel="stylesheet" href="bootstrap/dropdowns-enhancement.css"> 
        <link rel="stylesheet" href="bootstrap/dropdowns-enhancement.min.css"> 
        <title>Aufgabe hinzuf&uuml;gen</title>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#b_hilfe").click(function (event) {
                    event.preventDefault();
                    $('.btn').prop("disabled", false); // Element(s) are now enabled.
                });
            });



        </script>
    </head>
    <%
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();


    %>


    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
                    <div class="col-md-3"></div>
            <div class="col-md-6">


                <form action="DoInsertAufgabe.do" method="POST">

                    <label>Kategorie </label>
                    <br>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">M&ouml;gliche Kategorien <span class="caret"></span> </button>
                        <ul class="dropdown-menu">

                            <li><input required type="radio" id="kategorie1" name="kategorieId" value="1" > <label for="kategorie1">Basics</label></li>
                            <li><input required type="radio" id="kategorie2" name="kategorieId" value="2"> <label for="kategorie2">Strings</label></li>
                            <li><input required type="radio" id="kategorie3" name="kategorieId" value="3" > <label for="kategorie3">Integers</label></li>
                        </ul>
                    </div> 
                    <br><br>

                    <label>Autor</label>
                    <div class="input-group">
                        <input name="autor" required type="text" class="form-control" placeholder="Mustermann" aria-describedby="basic-addon1">
                    </div>
                    <br>


                    <label>Datentyp:</label>
                    <br>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">M&ouml;gliche Datentypen <span class="caret"></span> </button>
                        <ul class="dropdown-menu">

                            <li><input required type="radio" id="String" name="datentyp" value="String" > <label for="String">String</label></li>
                            <li><input required type="radio" id="StringArr" name="datentyp" value="String[]"> <label for="StringArr">String[]</label></li>
                            <li><input required type="radio" id="StringArrArr" name="datentyp" value="String[][]" > <label for="StringArrArr">String[][]</label></li>
                            <li><input required type="radio" id="Integer" name="datentyp" value="Integer"> <label for="Integer">Integer</label></li>
                            <li><input required type="radio" id="IntegerArr" name="datentyp" value="Integer[]"> <label for="IntegerArr">Integer[]</label></li>
                            <li><input required type="radio" id="IntegerArrArr" name="datentyp" value="Integer[][]"> <label for="IntegerArrArr">Integer[][]</label></li>
                            <li><input required type="radio" id="Double" name="datentyp" value="Double"> <label for="Double">Double</label></li>
                            <li><input required type="radio" id="DoubleArr" name="datentyp" value="Double[]"> <label for="DoubleArr">Double[]</label></li>
                            <li><input required type="radio" id="DoubleArrArr" name="datentyp" value="Double[][]"> <label for="DoubleArrArr">Double[][]</label></li>
                            <li><input required type="radio" id="Float" name="datentyp" value="Float"> <label for="Float">Float</label></li>
                            <li><input required type="radio" id="Boolean" name="datentyp" value="Boolean"> <label for="Boolean">Boolean</label></li>
                        </ul>
                    </div> 

                    <br><br>

                    <label>Namen der Aufgabe und Methode</label>
                    <div class="input-group">
                        <input name="methodenname" required type="text" class="form-control" placeholder="Methodenname" aria-describedby="basic-addon2">
                    </div>


                    <br>
                    <label>Parameter</label>
                    <br>

                    <div class="form-group" style="width:600px">
                        <div class="input-group">
                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">1. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input required type="radio" id="String1" name="parameter1" value="String"> <label for="String1">String</label></li>
                                    <li><input required type="radio" id="StringArr1" name="parameter1" value="String[]"> <label for="StringArr1">String[]</label></li>
                                    <li><input required type="radio" id="StringArrArr1" name="parameter1" value="String[][]" > <label for="StringArrArr1">String[][]</label></li>
                                    <li><input required type="radio" id="Integer1" name="parameter1" value="Integer"> <label for="Integer1">Integer</label></li>
                                    <li><input required type="radio" id="IntegerArr1" name="parameter1" value="Integer[]"> <label for="IntegerArr1">Integer[]</label></li>
                                    <li><input required type="radio" id="IntegerArrArr1" name="parameter1" value="Integer[][]"> <label for="IntegerArrArr1">Integer[][]</label></li>
                                    <li><input required type="radio" id="Double1" name="parameter1" value="Double"> <label for="Double1">Double</label></li>
                                    <li><input required type="radio" id="DoubleArr1" name="parameter1" value="Double[]"> <label for="DoubleArr1">Double[]</label></li>
                                    <li><input required type="radio" id="DoubleArrArr1" name="parameter1" value="Double[][]"> <label for="DoubleArrArr1">Double[][]</label></li>
                                    <li><input required type="radio" id="Float1" name="parameter1" value="Float"> <label for="Float1">Float</label></li>
                                    <li><input required type="radio" id="Boolean1" name="parameter1" value="Boolean"> <label for="Boolean1">Boolean</label></li>
                                </ul>
                            </span> 
                            <input required type="text" class="form-control" id="appendedInput" name="parametername1">

                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">2. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input type="radio" id="String2" name="parameter2" value="String" > <label for="String2">String</label></li>
                                    <li><input type="radio" id="StringArr2" name="parameter2" value="String[]"> <label for="StringArr2">String[]</label></li>
                                    <li><input type="radio" id="StringArrArr2" name="parameter2" value="String[][]" > <label for="StringArrArr2">String[][]</label></li>
                                    <li><input type="radio" id="Integer2" name="parameter2" value="Integer"> <label for="Integer2">Integer</label></li>
                                    <li><input type="radio" id="IntegerArr2" name="parameter2" value="Integer[]"> <label for="IntegerArr2">Integer[]</label></li>
                                    <li><input type="radio" id="IntegerArrArr2" name="parameter2" value="Integer[][]"> <label for="IntegerArrArr2">Integer[][]</label></li>
                                    <li><input type="radio" id="Double2" name="parameter2" value="Double"> <label for="Double2">Double</label></li>
                                    <li><input type="radio" id="DoubleArr2" name="parameter2" value="Double[]"> <label for="DoubleArr2">Double[]</label></li>
                                    <li><input type="radio" id="DoubleArrArr2" name="parameter2" value="Double[][]"> <label for="DoubleArrArr2">Double[][]</label></li>
                                    <li><input type="radio" id="Float2" name="parameter2" value="Float"> <label for="Float2">Float</label></li>
                                    <li><input type="radio" id="Boolean2" name="parameter2" value="Boolean"> <label for="Boolean2">Boolean</label></li>
                                </ul>
                            </span> 
                            <input type="text" class="form-control" id="appendedInput" name="parametername2">

                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">3. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input type="radio" id="String3" name="parameter3" value="String" > <label for="String3">String</label></li>
                                    <li><input type="radio" id="StringArr3" name="parameter3" value="String[]"> <label for="StringArr3">String[]</label></li>
                                    <li><input type="radio" id="StringArrArr3" name="parameter3" value="String[][]" > <label for="StringArrArr3">String[][]</label></li>
                                    <li><input type="radio" id="Integer3" name="parameter3" value="Integer"> <label for="Integer3">Integer</label></li>
                                    <li><input type="radio" id="IntegerArr3" name="parameter3" value="Integer[]"> <label for="IntegerArr3">Integer[]</label></li>
                                    <li><input type="radio" id="IntegerArrArr3" name="parameter3" value="Integer[][]"> <label for="IntegerArrArr3">Integer[][]</label></li>
                                    <li><input type="radio" id="Double3" name="parameter3" value="Double"> <label for="Double3">Double</label></li>
                                    <li><input type="radio" id="DoubleArr3" name="parameter3" value="Double[]"> <label for="DoubleArr3">Double[]</label></li>
                                    <li><input type="radio" id="DoubleArrArr3" name="parameter3" value="Double[][]"> <label for="DoubleArrArr3">Double[][]</label></li>
                                    <li><input type="radio" id="Float3" name="parameter3" value="Float"> <label for="Float3">Float</label></li>
                                    <li><input type="radio" id="Boolean3" name="parameter3" value="Boolean"> <label for="Boolean3">Boolean</label></li>
                                </ul>
                            </span> 
                            <input type="text" class="form-control" id="appendedInput" name="parametername3">
                        </div>

                        <!------------------------------------------------------------------------------

                        -------------------------------------------------------------------------------->

                    </div>
                    <label>Ehrfarung: </label>
                    <div class="form-group" style="width: 500px">
                        <div class="input-group">
                            <label class="input-group-addon">Mindest Level </label>
                            <input required type="number" min="1" class="form-control" id="appendedInput" name="level">
                            <span class="input-group-btn btn-group"> </span>

                            <label class="input-group-addon">Exp</label>
                            <input  required type="number" min="1" max="125" class="form-control" id="appendedInput" name="exp">
                            <span class="input-group-btn btn-group"> </span>
                        </div>
                    </div>
                    <br>
                    <label>Beschreibung:</label>
                    <br>
                    <textarea class="form-control" name="beschreibung" rows="4" cols="20"></textarea>
                    <br>
                    <div class="col-md-3">
                        <button class="btn btn-primary" id="b_email" type="submit"> Test hinzufügen??</button>
                        <br><br>
                        <input type="button" id="b_hilfe" value="Hilfe" class="btn btn-info"/>
                    </div>
                </form>
                <br><br>
            </div>
            <!-------- Script files 
            <script src="bootstrap/jquery-2.1.4.min.js"></script>
            <script src="bootstrap/js/bootstrap.min.js"></script>
            <script src="bootstrap/dropdowns-enhancement.js"></script> 
                            Script files -------------------------->
        <jsp:include page="scriptFiles.jsp"></jsp:include>
    </body>
</html>
