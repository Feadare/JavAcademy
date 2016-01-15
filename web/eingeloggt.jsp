<li><a href="/JavAcademy/profile.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Mein Profil</a></li>
</ul>
<div>
    <form class="navbar-form navbar-right" role="Ausloggen" action="/JavAcademy/logout.jsp" method="POST">
        <div class="form-group">
            <div>
            <%=session.getAttribute("username")%> / Level: <%=session.getAttribute("level")%>
            </div>
            <%=session.getAttribute("progress")%>
        </div>
        
        <button style="margin-left:10px" type="submit" class="btn btn-default">Logout</button>
    </form>