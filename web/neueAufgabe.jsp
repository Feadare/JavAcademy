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
    </head>
    <%
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();
        String autor = (String) session.getAttribute("username");
        String meldung ="";
        
        if (autor == null || autor =="null" || autor==""){
            meldung ="Nicht angemeldet! Speichern der Aufgabe nicht möglich.";
        }
        else
        {
            meldung="Für eine Fehlerfreie Aufgabe bitte die Eingaben Norm gerecht halten.<br>" 
                    +"Arrays in folgender Form [1, 2, 3] <br>"
                    +"Zweidimensionale Arrays in folgender Form [[1, 2, 3],[4, 5 ,6]] <br>"
                    + "Doubles mit . trennen -> 12.3 <br>"
                    + "Booleans nur mit true oder false <br>";
        }

    %>


    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
            <div class="col-md-3"></div>
            <div class="col-md-6">
                
            <%=meldung%>
            <br>
                
                <form action="DoInsertAufgabe.do" method="POST">

                    <label>Kategorie </label>
                    <br>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">M&ouml;gliche Kategorien <span class="caret"></span> </button>
                        <ul class="dropdown-menu">

                            <li><input required type="radio" id="kategorie1" name="kategorieId" value="1" > <label for="kategorie1">Basics</label></li>
                            <li><input required type="radio" id="kategorie2" name="kategorieId" value="2"> <label for="kategorie2">Fallunterscheidungen</label></li>
                            <li><input required type="radio" id="kategorie3" name="kategorieId" value="3" > <label for="kategorie3">Schleifen</label></li>
                            <li><input required type="radio" id="kategorie4" name="kategorieId" value="4" > <label for="kategorie4">Strings</label></li>
                            <li><input required type="radio" id="kategorie5" name="kategorieId" value="5"> <label for="kategorie5">Logik</label></li>
                            <li><input required type="radio" id="kategorie6" name="kategorieId" value="6" > <label for="kategorie6">Zahlen</label></li>
                            <li><input required type="radio" id="kategorie7" name="kategorieId" value="7" > <label for="kategorie7">Arrays</label></li>
                            <li><input required type="radio" id="kategorie8" name="kategorieId" value="8"> <label for="kategorie8">Felder</label></li>
                            <li><input required type="radio" id="kategorie9" name="kategorieId" value="9" > <label for="kategorie9">Fortgeschrittene</label></li>
                            <li><input required type="radio" id="kategorie10" name="kategorieId" value="10" > <label for="kategorie10">Andere</label></li>
                        </ul>
                    </div> 
                    <br><br>

                    <label>Datentyp:</label>
                    <br>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">M&ouml;gliche Datentypen <span class="caret"></span> </button>
                        <ul class="dropdown-menu">

                            <li><input required type="radio" id="String" name="datentyp" value="String" > <label for="String">String</label></li>
                            <li><input required type="radio" id="StringArr" name="datentyp" value="String[]"> <label for="StringArr">String[]</label></li>
                            <li><input required type="radio" id="StringArrArr" name="datentyp" value="String[][]" > <label for="StringArrArr">String[][]</label></li>
                            <li class="divider"></li>
                            <li><input required type="radio" id="Integer" name="datentyp" value="Integer"> <label for="Integer">Integer</label></li>
                            <li><input required type="radio" id="IntegerArr" name="datentyp" value="Integer[]"> <label for="IntegerArr">Integer[]</label></li>
                            <li><input required type="radio" id="IntegerArrArr" name="datentyp" value="Integer[][]"> <label for="IntegerArrArr">Integer[][]</label></li>
                            <li class="divider"></li>
                            <li><input required type="radio" id="Double" name="datentyp" value="Double"> <label for="Double">Double</label></li>
                            <li><input required type="radio" id="DoubleArr" name="datentyp" value="Double[]"> <label for="DoubleArr">Double[]</label></li>
                            <li><input required type="radio" id="DoubleArrArr" name="datentyp" value="Double[][]"> <label for="DoubleArrArr">Double[][]</label></li>
                            <li class="divider"></li>
                            <li><input required type="radio" id="Boolean" name="datentyp" value="Boolean"> <label for="Boolean">Boolean</label></li>
                            <li><input required type="radio" id="BooleanArr" name="datentyp" value="Boolean[]"> <label for="BooleanArr">Boolean[]</label></li>
                            <li><input required type="radio" id="BooleanArrArr" name="datentyp" value="Boolean[][]"> <label for="BooleanArr">Boolean[][]</label></li>
                            <li class="divider"></li>
                            <li><input required type="radio" id="Float" name="datentyp" value="Float"> <label for="Float">Float</label></li>
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
                                    <li class="divider"></li>
                                    <li><input required type="radio" id="Integer1" name="parameter1" value="Integer"> <label for="Integer1">Integer</label></li>
                                    <li><input required type="radio" id="IntegerArr1" name="parameter1" value="Integer[]"> <label for="IntegerArr1">Integer[]</label></li>
                                    <li class="divider"></li>
                                    <li><input required type="radio" id="Double1" name="parameter1" value="Double"> <label for="Double1">Double</label></li>
                                    <li><input required type="radio" id="DoubleArr1" name="parameter1" value="Double[]"> <label for="DoubleArr1">Double[]</label></li>
                                    <li class="divider"></li>
                                    <li><input required type="radio" id="Boolean1" name="parameter1" value="Boolean"> <label for="Boolean1">Boolean</label></li>
                                    <li><input required type="radio" id="BooleanArr1" name="parameter1" value="Boolean[]"> <label for="BooleanArr1">Boolean[]</label></li>
                                    <li class="divider"></li>
                                    <li><input required type="radio" id="Float1" name="parameter1" value="Float"> <label for="Float1">Float</label></li>
                                </ul>
                            </span> 
                            <input required type="text" class="form-control" id="appendedInput" name="parametername1">

                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">2. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input  type="radio" id="default2" name="parameter2" value="FRESHDUMMYDORE"> <label for="default2">2. Datentyp</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="String2" name="parameter2" value="String" > <label for="String2">String</label></li>
                                    <li><input type="radio" id="StringArr2" name="parameter2" value="String[]"> <label for="StringArr2">String[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Integer2" name="parameter2" value="Integer"> <label for="Integer2">Integer</label></li>
                                    <li><input type="radio" id="IntegerArr2" name="parameter2" value="Integer[]"> <label for="IntegerArr2">Integer[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Double2" name="parameter2" value="Double"> <label for="Double2">Double</label></li>
                                    <li><input type="radio" id="DoubleArr2" name="parameter2" value="Double[]"> <label for="DoubleArr2">Double[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Boolean2" name="parameter2" value="Boolean"> <label for="Boolean2">Boolean</label></li>
                                    <li><input type="radio" id="BooleanArr2" name="parameter2" value="Boolean[]"> <label for="BooleanArr2">Boolean[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Float2" name="parameter2" value="Float"> <label for="Float2">Float</label></li>
                                </ul>
                            </span> 
                            <input type="text" class="form-control" id="appendedInput" name="parametername2">

                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">3. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input  type="radio" id="default3" name="parameter3" value="FRESHDUMMYDORE"> <label for="default3">3. Datentyp</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="String3" name="parameter3" value="String" > <label for="String3">String</label></li>
                                    <li><input type="radio" id="StringArr3" name="parameter3" value="String[]"> <label for="StringArr3">String[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Integer3" name="parameter3" value="Integer"> <label for="Integer3">Integer</label></li>
                                    <li><input type="radio" id="IntegerArr3" name="parameter3" value="Integer[]"> <label for="IntegerArr3">Integer[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Double3" name="parameter3" value="Double"> <label for="Double3">Double</label></li>
                                    <li><input type="radio" id="DoubleArr3" name="parameter3" value="Double[]"> <label for="DoubleArr3">Double[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Boolean3" name="parameter3" value="Boolean"> <label for="Boolean3">Boolean</label></li>
                                    <li><input type="radio" id="BooleanArr3" name="parameter3" value="Boolean[]"> <label for="BooleanArr3">Boolean[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Float3" name="parameter3" value="Float"> <label for="Float3">Float</label></li>
                                </ul>
                            </span> 
                            <input type="text" class="form-control" id="appendedInput" name="parametername3">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">4. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input  type="radio" id="default4" name="parameter4" value="FRESHDUMMYDORE"> <label for="default4">4. Datentyp</label></li>
                                    <li class="divider"></li>
                                    <li><input  type="radio" id="String4" name="parameter4" value="String"> <label for="String4">String</label></li>
                                    <li><input  type="radio" id="StringArr4" name="parameter4" value="String[]"> <label for="StringArr4">String[]</label></li>
                                    <li class="divider"></li>
                                    <li><input  type="radio" id="Integer4" name="parameter4" value="Integer"> <label for="Integer4">Integer</label></li>
                                    <li><input  type="radio" id="IntegerArr4" name="parameter4" value="Integer[]"> <label for="IntegerArr4">Integer[]</label></li>
                                    <li class="divider"></li>
                                    <li><input  type="radio" id="Double4" name="parameter4" value="Double"> <label for="Double4">Double</label></li>
                                    <li><input  type="radio" id="DoubleArr4" name="parameter4" value="Double[]"> <label for="DoubleArr4">Double[]</label></li>
                                    <li class="divider"></li>
                                    <li><input  type="radio" id="Boolean4" name="parameter4" value="Boolean"> <label for="Boolean4">Boolean</label></li>
                                    <li><input  type="radio" id="Boolean4" name="parameter4" value="Boolean[]"> <label for="BooleanArr4">Boolean[]</label></li>
                                    <li class="divider"></li>
                                    <li><input  type="radio" id="Float4" name="parameter4" value="Float"> <label for="Float4">Float</label></li>
                                </ul>
                            </span> 
                            <input  type="text" class="form-control" id="appendedInput" name="parametername4">

                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">5. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input  type="radio" id="default5" name="parameter5" value="FRESHDUMMYDORE"> <label for="default5">5. Datentyp</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="String5" name="parameter5" value="String" > <label for="String5">String</label></li>
                                    <li><input type="radio" id="StringArr5" name="parameter5" value="String[]"> <label for="StringArr5">String[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Integer5" name="parameter5" value="Integer"> <label for="Integer5">Integer</label></li>
                                    <li><input type="radio" id="IntegerArr5" name="parameter5" value="Integer[]"> <label for="IntegerArr5">Integer[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Double5" name="parameter5" value="Double"> <label for="Double5">Double</label></li>
                                    <li><input type="radio" id="DoubleArr5" name="parameter5" value="Double[]"> <label for="DoubleArr5">Double[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Boolean5" name="parameter5" value="Boolean"> <label for="Boolean5">Boolean</label></li>
                                    <li><input type="radio" id="BooleanArr5" name="parameter5" value="Boolean[]"> <label for="BooleanArr5">Boolean[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Float5" name="parameter5" value="Float"> <label for="Float5">Float</label></li>
                                </ul>
                            </span> 
                            <input type="text" class="form-control" id="appendedInput" name="parametername5">

                            <span class="input-group-btn btn-group">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:100px">6. Datentyp <span class="caret"></span> </button>
                                <ul class="dropdown-menu">
                                    <li><input  type="radio" id="default6" name="parameter6" value="FRESHDUMMYDORE"> <label for="default6">6. Datentyp</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="String6" name="parameter6" value="String" > <label for="String6">String</label></li>
                                    <li><input type="radio" id="StringArr6" name="parameter6" value="String[]"> <label for="StringArr6">String[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Integer6" name="parameter6" value="Integer"> <label for="Integer6">Integer</label></li>
                                    <li><input type="radio" id="IntegerArr6" name="parameter6" value="Integer[]"> <label for="IntegerArr6">Integer[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Double6" name="parameter6" value="Double"> <label for="Double6">Double</label></li>
                                    <li><input type="radio" id="DoubleArr6" name="parameter6" value="Double[]"> <label for="DoubleArr6">Double[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Boolean6" name="parameter6" value="Boolean"> <label for="Boolean6">Boolean</label></li>
                                    <li><input type="radio" id="BooleanArr6" name="parameter6" value="Boolean[]"> <label for="BooleanArr6">Boolean[]</label></li>
                                    <li class="divider"></li>
                                    <li><input type="radio" id="Float6" name="parameter6" value="Float"> <label for="Float6">Float</label></li>
                                </ul>
                            </span> 
                            <input type="text" class="form-control" id="appendedInput" name="parametername6">
                        </div>

                        <!------------------------------------------------------------------------------

                        -------------------------------------------------------------------------------->

                    </div>
                    <label>Erfahrung: </label>
                    <div class="form-group" style="width: 500px">
                        <div class="input-group">
                            <label class="input-group-addon">Empfohlenes Level </label>
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
                    <input type="hidden" name="autor" value="<%=autor%>"> 
                    <div class="col-md-3">
                        <button class="btn btn-primary" id="b_test" type="submit"> Aufgabe hinzufügen</button>
                        <br>
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
