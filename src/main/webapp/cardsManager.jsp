<%@ page import="ua.kpi.cardgame.entities.Card" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: miyatna
  Date: 20.06.2022
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="bootstrap.html" %>
    <title>Cards Manager</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid w-75" style="height: 2.5rem;">
            <a id="back_btn"class="btn btn-primary" href="/admin">Back</a>
        </div>
    </nav>

    <h1>Card Manager</h1>

    <ul class="list-group mt-3">


        <%
            String cardsElement = " ";

            List<Card> users = (List<Card>) request.getAttribute("cards");

            if (users == null) {
                cardsElement += "NULL";
            } else if (users.size() != 0) {
                for (int i = 0; i < users.size(); i++) {
                    Card card1 = users.get(i);
                    if (card1 == null)
                        cardsElement += "<li class=\"list-group-item d-flex justify-content-between align-items-center\">NULL</li>";

                    cardsElement += "<li class=\"list-group-item d-flex justify-content-between align-items-center\">" + card1.getResource();


                    cardsElement += "<a href=\"/changeCard?id=" + card1.getCardId() + "\" class=\"btn btn-primary\">Edit</a>"
                            + "</li>";
                }
            }
        %>

        <%=cardsElement%>
    </ul>

</body>
</html>
