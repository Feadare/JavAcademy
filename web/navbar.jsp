<%-- 
    Document   : htmlVorlage
    Created on : 08.12.2015, 09:33:16
    Author     : wilmanm
--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/JavAcademyCool/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home <span class="sr-only">(current)</span></a></li>
            

            
                <%
                    if(session.getAttribute("username") == null)
                    {    
                        %>
                        <jsp:include page="gast.jsp" />
                        <%
                    }
                    else
                    {
                        %>
                        <jsp:include page="eingeloggt.jsp" />
                        <% 
                    }
                
                %>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="top10.jsp">Top10</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>



