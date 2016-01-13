<%-- 
    Document   : top10
    Created on : 04.12.2015, 14:07:24
    Author     : wilmanm
--%>

<%@page import="SQL.InBetween"%>
<%@page import="SQL.DbTools"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    </head>
    <%
        Connection con = DbTools.connect();
        Statement stmt = con.createStatement();
        String top10 = InBetween.getTop10(stmt);

    %>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="col-md-1">
            
        </div>
        
        <div class="col-md-10">
            <%=top10%>
        </div>
        
        <div class="col-md-1">
            
        </div>
        
    </body>
</html>
