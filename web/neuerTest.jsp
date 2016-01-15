<%-- 
    Document   : neuerTest
    Created on : 03.12.2015, 11:55:00
    Author     : JavAcademy
--%>

<%@page import="SQL.DbTools"%>
<%@page import="SQL.InBetween"%>
<%@page import="java.util.Arrays"%>
<%@page import="compiler.EingabenCompiler"%>
<%@page import="javax.tools.JavaFileObject"%>
<%@page import="javax.tools.JavaCompiler"%>
<%@page import="framework.Parameter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AufgabenVorlage</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <script>
        function submitForm(elem) {
            document.getElementById("btnPressed").value = elem;
            document.frm2.submit();
        }
    </script>
    <style type="text/css">
        table { page-break-inside:auto }
        tr    { page-break-inside: auto; page-break-after:auto }
        td    { page-break-inside: auto; page-break-after:auto }
        td    { font-size: 1em;}
        textarea {resize: none}
    </style>
</head>
<%
    Connection con = DbTools.connect();
    Statement stmt = con.createStatement();
    Object uErgebnis = null;

    String methodennamen = request.getParameter("methodenname");

    int aufgabenid = InBetween.getAufgabenId(stmt, methodennamen);
    int testid = 0;
    testid = InBetween.getTestId(stmt, aufgabenid);
    boolean dummyTest = InBetween.getTestDummy(stmt, aufgabenid);
    if(!dummyTest)
    {
        testid = testid +1;
    }

    int anzahlParameter = InBetween.getTestParameterAnzahl(stmt, aufgabenid);
    String aufgabenbeschreibung = InBetween.getAufgabenbeschreibung(stmt, aufgabenid);
    String methodenname = InBetween.getMethodennamen(stmt, aufgabenid);
    String datentyp = InBetween.getDatentyp(stmt, aufgabenid);

    ArrayList<Parameter> aufgabenParameter = InBetween.getAufgabenParameter(stmt, aufgabenid);

    String parameterUebersicht = "";
    parameterUebersicht += InBetween.testParameterListe(aufgabenParameter);

    String parameterString = "";

    for (Parameter parameter1 : aufgabenParameter) {
        parameterString += parameter1;
        parameterString += ", ";
    }
    parameterString = parameterString.substring(0, parameterString.length() - 2);

    String code = request.getParameter("code");
    if (code == null || code.isEmpty()) {
        code = "";
    }

    uErgebnis = (Object) request.getAttribute("uErgebnis");
    request.setAttribute("aufgabenid", aufgabenid);

    // session.setAttribute("aufgabenid", String.valueOf(aufgabenid));
    int anzahlTests = InBetween.getAnzahlTests(stmt, aufgabenid);
    String anzahlTestText = "Es sind " + anzahlTests + " vorhanden!";

    if (anzahlTests == 1) {
        anzahlTestText = "Es ist 1 Test vorhanden!";
    }

%>
<body>

    <jsp:include page="navbar.jsp"></jsp:include>

        <div class="col-md-3">

        </div>

        <div class="col-md-6" id="aufgabenBeschreibung">
            <form action="DoInsertTest.do" method="POST">
            <%=anzahlTestText%> <br>
            Neue Test ID: <%=testid%>


            <br><br>
            <%=parameterUebersicht%>
            <br><br>

            <div class="input-group">
                <label class="input-group-addon"> Erwartetes Ergebnis </label>
                <input required type="text" class="form-control" id="appendedInput" name="result">
                <span class="input-group-btn btn-group"> </span>
            </div>
            <br>

            <input type="hidden" name="aufgabenid" value="<%=aufgabenid%>"> 
            <input type="hidden" name="anzahlParameter" value="<%=anzahlParameter%>"> 
            <input type="hidden" name="methodenname" value="<%=methodenname%>"> 
            <input type="hidden" name="testid" value="<%=testid%>"> 

            <input type="submit" value="Test speichern"/> 
        </form>   

        <br>
        <%=aufgabenid%> //  <%=aufgabenbeschreibung%> <br><br>

        public <%=datentyp%> <%=methodenname%>(<%=parameterString%>) {

        <form action="DoTestCompiler.do" method="POST">

            <textarea name="code" rows="10" cols="50"><%=code%></textarea>  <br> } <br>
            <input type="hidden" name="methodenname" value="<%=methodenname%>"> 

            <input type="submit" value="Test pr&uuml;fen" /> 
        </form>   
        <br>
        <div class="col-md-4"> <%=uErgebnis%> </div>
    </div>



    <jsp:include page="scriptFiles.jsp"></jsp:include>
</body>
</html>
