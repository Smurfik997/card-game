<%@ page import="ua.kpi.cardgame.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="bootstrap.html" %>
    <title>Admin panel</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid w-75" style="height: 2.5rem;">
            <a id="back_btn"class="btn btn-primary" href="/admin">Back</a>
        </div>
    </nav>

    <h1>User Manager</h1>

    <form action="/api/searchUser">
        <div class="form-group">
            <label for="usernameInput">Search user by username</label>
            <input type="text" name="username" class="form-control" id="usernameInput" placeholder="Enter username">
            <%String message = (String) request.getAttribute("message");
                if (message == null) {
                    message="";
                }%>
            <p  style="color: red; font-size: 14px">
                <%=message%>
            </p>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <%
        String foundUser = " ";

        User user2 = (User) request.getAttribute("foundUser");
        if (user2 != null) {
            String role = (String) request.getAttribute("role");
            foundUser = "<ul class=\"list-group mt-3\"> <li class=\"list-group-item d-flex justify-content-between align-items-center list-group-item-success\">" + user2.getLogin();

            if (role == null)
                foundUser += "<a href=\"/api/promote?id=" + user2.getUserId() + "\" class=\"btn btn-primary\">Promote</a>"
                        + "<a href=\"/api/ban?id=" + user2.getUserId() + "\" class=\"btn btn-outline-danger\">Ban</a></ul>"
                        + "</li>";


            else if (!role.equals("admin")) {

                if (user2.getStatus() == null)
                    foundUser += "<a href=\"/api/promote?id=" + user2.getUserId() + "\" class=\"btn btn-primary\">Promote</a>"
                            + "<a href=\"/api/ban?id=" + user2.getUserId() + "\" class=\"btn btn-outline-danger\">Ban</a></ul>"
                            + "</li>";

                else if (user2.getStatus().equals("banned"))
                    foundUser += "<a href=\"/api/unban?id=" + user2.getUserId() + "\" class=\"btn btn-outline-danger\">Unban</a></ul>"
                            + "</li>";
                else
                    foundUser += "<a href=\"/api/promote?id=" + user2.getUserId() + "\" class=\"btn btn-primary\">Promote</a>"
                            + "<a href=\"/api/ban?id=" + user2.getUserId() + "\" class=\"btn btn-outline-danger\">Ban</a></ul>"
                            + "</li>";
            } else
                foundUser += "<span> This user is admin. You can't modify him.</span>" + "</li>";
        }
    %>

    <%=foundUser%>

    <ul class="list-group mt-3">


        <%
            String usersElement = " ";

            List<User> users = (List<User>) request.getAttribute("users");

            if (users == null) {
                usersElement += "NULL";
            } else if (users.size() != 0) {
                for (int i = 0; i < users.size(); i++) {
                    User user1 = users.get(i);
                    if (user1 == null)
                        usersElement += "<li class=\"list-group-item d-flex justify-content-between align-items-center\">NULL</li>";

                    usersElement += "<li class=\"list-group-item d-flex justify-content-between align-items-center\">" + user1.getLogin();

                    if (user1.getStatus() == null)
                            usersElement += "<a href=\"/api/promote?id=" + user1.getUserId() + "\" class=\"btn btn-primary\">Promote</a>"
                            + "<a href=\"/api/ban?id=" + user1.getUserId() + "\" class=\"btn btn-outline-danger\">Ban</a></ul>"
                            + "</li>";

                    else if (user1.getStatus().equals("banned"))
                        usersElement += "<a href=\"/api/unban?id=" + user1.getUserId() + "\" class=\"btn btn-outline-danger\">Unban</a></ul>"
                                + "</li>";
                    else
                        usersElement += "<a href=\"/api/promote?id=" + user1.getUserId() + "\" class=\"btn btn-primary\">Promote</a>"
                                + "<a href=\"/api/ban?id=" + user1.getUserId() + "\" class=\"btn btn-outline-danger\">Ban</a></ul>"
                                + "</li>";
                }
            }
        %>

        <%=usersElement%>
    </ul>

</body>
</html>
