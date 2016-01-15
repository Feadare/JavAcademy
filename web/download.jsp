<%-- 
    Document   : download
    Created on : 13.01.2016, 09:35:56
    Author     : feadare
--%>

<%@page import="SQL.InBetween"%>
<%@page import="SQL.DbTools"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DB Download</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <%    
        String dlink = "";
        if(session.getAttribute("username").equals("admin")){
            DbTools.exportDb(DbTools.connect());
            dlink = "javaacademydbbackup.sql";
        }
        
    %>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="col-md-1">
            
        </div>
        
        <div class="col-md-10">
            <a class="btn btn-primary" href="<%=dlink%>">Datenbank-Backup runterladen</a>
        </div>
        
        <div class="col-md-1">
            
        </div>
        
    </body>
</html>
