<li><a href="admin.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Admin</a></li>
</ul>
<div>
<form class="navbar-form navbar-right" role="Ausloggen" action="doLogout.do" method="POST">
    <div class="form-group">
        <%=session.getAttribute("username")%>
    </div>
    <button style="margin-left:10px" type="submit" class="btn btn-default">Logout</button>
</form>