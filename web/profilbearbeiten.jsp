<%-- 
    Document   : profilbearbeiten
    Created on : 08.12.2015, 08:56:17
    Author     : palamito
--%>

<%@page import="SQL.DbTools"%>
<%@page import="SQL.InBetween"%>
<%@page import="framework.Benutzer"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil bearbeiten</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css"> 
        <link rel="stylesheet" href="css/profilbearbeiten.css"> 
    </head>
    <%
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();

        String username = (String) session.getAttribute("username");

        String ausgabe = "";
        //boolean dbCheck = true;
        String konsole = "";
        try {
            konsole = (String) request.getAttribute("konsole");
        } catch (NullPointerException e) {
            konsole = "";
        }
        if ("false".equals(request.getAttribute("dbCheck"))) {
            ausgabe = "<div class=\"alert alert-danger\" role=\"alert\">Passwort falsch! </div>";
        }
        if(konsole == null){konsole="";}
        int id = Benutzer.getIDbyName(username, con);

        String name = InBetween.myName(stmt, id);
        String email = InBetween.myEmail(stmt, id);
    %>

    <script type="text/javascript">
        function checkDbPassword(dbstr, altstr) {
            if (!dbstr === altstr) {
                alert("Altes Passwort stimmt nicht überein")
                return false;
            }
        }

        function checkPassword(str)
        {
            var re = /^(?=.*\d)(?=.*[a-z]).{8,}$/;
            return re.test(str);
        }

        function checkForm(form)
        {
            if (form.newpswd.value !== "" && form.newpswd.value === form.newpswdok.value) {
                if (!checkPassword(form.newpswd.value)) {
                    alert("Das Passwort muss mindestens 8 Zeichen lang sein und eine Zahl\n\
     sowie einen Buchstaben enthalten.");
                    form.newpswd.focus();
                    return false;
                }
            } else {
                alert("Passwörter stimmen nicht überein!!");
                form.newpswd.focus();
                return false;
            }
            return true;
        }

    </script>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
            <div class="col-md-4"></div>
            <div class="col-md-5">
                <h1>Profil Bearbeiten</h1>
                <br><br>
                <div class="col-md-12">
                    <div class="col-md-6">
                        Name: <br>
                        <input type="text" name="name" readonly="readonly" value="<%=name%>" /><br><br>
                </div>
            </div>

            <form action="DoDbPassCheck.do" method="POST" onsubmit="return checkForm(this);">
                <div class ="col-md-12">
                    <div class="col-md-6">
                        Neues Passwort:<br>
                        <input type="password" name="newpswd" value="" />  
                    </div>
                    <div class="col-md-6">
                        Passwort wiederholen:<br>
                        <input type="password" name="newpswdok" value="" /><br><br>
                    </div>
                </div>

                <div class ="col-md-12">
                    <div class="col-md-6">
                        Altes Passwort:<br>
                        <input type="password" name="altpswd" value="" />  
                    </div>

                    <div class="col-md-6">

                        <br>
                        <button class="btn btn-danger" id="b_passwort" type="submit">Passwort &auml;ndern</button>
                        <br><br>
                    </div>
                </div>
            </form>

            <form action="DoEmail.do" method="POST">
                <div class="col-md-12">
                    <div class="col-md-6">
                        E-mail:<br>
                        <input type="text" name="email" value="<%=email%>" /><br>
                    </div>
                    <div class="col-md-6">
                        <br>
                        <button class="btn btn-primary" id="b_email" type="submit">E-mail &auml;ndern</button>
                    </div>
                </div>
            </form>
            <%=ausgabe%>
            <%=konsole%>
        </div>

        <div class="col-md-3"></div>


    </body>
</html>
