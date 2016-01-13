<%-- 
    Document   : aufgabenvorlage
    Created on : 03.12.2015, 11:55:00
    Author     : wilmanm
--%>

<%@page import="framework.Benutzer"%>
<%@page import="SQL.InBetween"%>
<%@page import="SQL.DbTools"%>
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
    <style type="text/css">
        table { page-break-inside:auto }
        tr    { page-break-inside: auto; page-break-after:auto }
        td    { page-break-inside: auto; page-break-after:auto }
        td    { font-size: 1em;}
        textarea {resize: none}
    </style>
    <%
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();

        int id = 0;
        int a_id = 0;
        String username = (String) session.getAttribute("username");
        int u_ID = Benutzer.getIDbyName(username, con);

        try {
            id = Integer.parseInt(request.getParameter("btnPressed"));
        } catch (NumberFormatException ex) {
            System.err.println("FEHLER: " + ex);
            String sa_id = request.getAttribute("id").toString();
            a_id = Integer.parseInt(sa_id);
        }

        if (id == 0) {
            id = a_id;
        }
        // boolean array = true;
        // boolean twodiarray = false;
        // Object[][] arr2D2Ergebnis = null;
        // Object[] arrErgebnis = null;
        Object uErgebnis = "";

        //String aufgabenbeschreibung = framework.InBetween.getAufgabenbeschreibung(stmt,id);
        //String benutzerAngemeldet = framework.InBetween.getLoginname();
        //String aufgabenbeschreibung = "Hey! In dieser Aufgabe muss du deinen Namen ausgeben :)"
        //        + "<br>Hey! In dieser Aufgabe muss du deinen Namen ausgeben :)"
        //        + "<br>nHey! In dieser Aufgabe muss du deinen Namen ausgeben :)";
        String aufgabenbeschreibung = InBetween.getAufgabenbeschreibung(stmt, id);

        String methodenname = InBetween.getMethodennamen(stmt, id);
        String datentyp = InBetween.getDatentyp(stmt, id);

        ArrayList<Parameter> aufgabenParameter = InBetween.getAufgabenParameter(stmt, id);
        String parameterString = "";
        for (Parameter parameter1 : aufgabenParameter) {
            parameterString += parameter1;
            parameterString += ", ";
        }
        parameterString = parameterString.substring(0, parameterString.length() - 2);

        String code = InBetween.getEingabe(stmt, u_ID, id);
        if (code.isEmpty()) {
            code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                code = "";
            }
        }

//if(array){
        //   if(twodiarray)
        //  {
        //     arr2D2Ergebnis = (Object[][]) request.getAttribute("arr2D2Ergebnis");
        // }
        // else{
        //     arrErgebnis = (Object[]) request.getAttribute("arrErgebnis");
        //  }
        //  }
        // else{
        //    aErgebnis = (Object) request.getAttribute("aErgebnis");
        // }
        // uErgebnis = Arrays.deepToString(arr2D2Ergebnis) + Arrays.toString(arrErgebnis) + aErgebnis;
        uErgebnis = (Object) request.getAttribute("uErgebnis");


    %>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>

            <div class="col-md-3">

            </div>



            <div class="col-md-5" id="aufgabenBeschreibung">

            <%=aufgabenbeschreibung%> <br><br>

            public <%=datentyp%> <%=methodenname%>(<%=parameterString%>) {

            <form action="DoCompiler.do" method="POST">
                <input type="hidden" name="a_ID" value=<%=id%> /> <br> 
                <textarea name="code" rows="10" cols="50"><%=code%></textarea>  <br> } <br>

                <input type="submit" value="Los" /> 
            </form>   
            <br>

        </div>

        <div class="col-md-4"> <%=uErgebnis%> </div>
    </body>
</html>
