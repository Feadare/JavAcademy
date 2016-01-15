<%-- 
    Document   : neueUser
    Created on : 04.12.2015, 08:39:58
    Author     : wilmanm
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="framework.Benutzer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Neues Mitglied</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <script type="text/javascript">

        function checkPassword(str)
        {
            var re = /^(?=.*\d)(?=.*[a-z, A-Z]).{8,}$/;
            return re.test(str);
        }

        function checkForm(form)
        {
            if (form.username.value == "") {
                alert("Username darf nicht leer sein!");
                form.username.focus();
                return false;
            }
            re = /^\w+$/;
            if (!re.test(form.username.value)) {
                alert("Username darf nur Zeichen, Zahlen und Underscores enthalten!");
                form.username.focus();
                return false;
            }
            if (form.passwort.value != "" && form.passwort.value == form.passwort2.value) {
                if (!checkPassword(form.passwort.value)) {
                    alert("Das Passwort muss mindestens 8 Zeichen lang sein und eine Zahl\n\
         sowie einen Buchstaben enthalten.");
                    form.passwort.focus();
                    return false;
                }
            } else {
                alert("Passwörter stimmen nicht überein!!");
                form.passwort.focus();
                return false;
            }
            return true;
        }

    </script>
            <%
                String ausgabe = "";
                        boolean isFree = true;
                        if ("false".equals(request.getAttribute("isFree"))) {
                            ausgabe = "<div class=\"alert alert-danger\" role=\"alert\">Username bereits vergeben! </div>";
                        }
                    %>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>  

            <div class="col-md-3">

            </div>

            <div class="col-md-6">
                <br>
                <form action="DoCheckUsername.do" method="POST" onsubmit="return checkForm(this);">

                    <label>Dein Benutzername</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">Nick</span>
                        <input name="username" required type="text" class="form-control" placeholder="Benutzername" aria-describedby="basic-addon1">
                </div>
                    <%=ausgabe%>

                <br>

                <label>Dein Passwort</label>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">supergeheim123</span>
                    <input name="passwort" required type="password" class="form-control" placeholder="Passwort" aria-describedby="basic-addon1">
                </div>

                <label>Passwort wiederholen</label>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">supergeheim123</span>
                    <input name="passwort2" required type="password" class="form-control" placeholder="Passwort" aria-describedby="basic-addon1">
                </div>

                <br>

                <label>Deine Email-Adresse</label>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">beispiel@domain.de</span>
                    <input name="email" required type="email" class="form-control" placeholder="E-Mailadresse" aria-describedby="basic-addon1">
                </div>

                <br>

                <button class="btn btn-default" type="submit">


                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Club beitreten ;)
                </button>
            </form>
        </div>

        <div class="col-md-3">

        </div>

    </body>
</html>
