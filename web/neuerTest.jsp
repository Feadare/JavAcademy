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
</head>
<%
    int kategorieID = 2;
    Connection con = DbTools.connect();
    Statement stmt = con.createStatement();

    ArrayList aufgabenliste = InBetween.aufgabenliste(kategorieID, stmt);
    ArrayList aufgabenIDliste = InBetween.aufgabenIDliste(kategorieID, stmt);
    String aufgabenuebersicht = "";
    aufgabenuebersicht += InBetween.aufgabenlisteHead();
    aufgabenuebersicht += InBetween.aufgabelisteAusgabe(aufgabenliste, aufgabenIDliste);
    aufgabenuebersicht += InBetween.aufgabenlisteFoot();

    Object uErgebnis = null;

    //String aufgabenbeschreibung = framework.InBetween.getAufgabenbeschreibung(stmt,id);
    //String benutzerAngemeldet = framework.InBetween.getLoginname();
    //String aufgabenbeschreibung = "Hey! In dieser Aufgabe muss du deinen Namen ausgeben :)"
    //        + "<br>Hey! In dieser Aufgabe muss du deinen Namen ausgeben :)"
    //        + "<br>nHey! In dieser Aufgabe muss du deinen Namen ausgeben :)";
    String methodennamen = request.getParameter("methodenname");

    int aufgabenid = aufgabenid = InBetween.getAufgabenId(stmt, methodennamen);
//Integer.parseInt((String) request.getParameter("aufgabenid"));

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


%>
<body>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">

                    <li class="active"><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home <span class="sr-only">(current)</span></a></li>


                    <li><a href="profile.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Mein Profil</a></li>
                </ul>

                <div>
                    <form class="navbar-form navbar-right" role="Einloggen">
                        <div class="form-group">
                            <input type="text" name="login" class="form-control" placeholder="Login">
                            <input type="text" name="password" class="form-control" placeholder="Password"/>
                        </div>
                        <button type="submit" class="btn btn-default">Login</button>
                    </form>

                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="top10.jsp">Top10</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="col-md-3">

    </div>


    <div class="col-md-6" id="aufgabenBeschreibung">
        
          <%=parameterUebersicht%>
          <br><br>
          <br><br>


        <%=aufgabenid%> //  <%=aufgabenbeschreibung%> <br><br>

        public <%=datentyp%> <%=methodenname%>(<%=parameterString%>) {

        <form action="DoCompiler.do" method="POST">
            <textarea name="code" rows="10" cols="50"><%=code%></textarea>  <br> } <br>

            <input type="submit" value="Los" /> 
        </form>   
        <br>
        Achtung: <%=uErgebnis%>
    </div>



    <jsp:include page="scriptFiles.jsp"></jsp:include>
</body>
</html>
