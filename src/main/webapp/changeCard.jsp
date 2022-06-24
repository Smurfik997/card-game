<%@ page import="ua.kpi.cardgame.entities.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="bootstrap.html" %>
    <title>Change card</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid w-75" style="height: 2.5rem;">
            <a id="back_btn"class="btn btn-primary" href="/cardsManager">Back</a>
        </div>
    </nav>

    <%Card currentCard = (Card) request.getAttribute("card");
        String message = "";
        if (currentCard == null)
            message="CARD IS NULL";
        else
            message="";
    %>

    <p> <%=message%> </p>

    <form action="/api/changeCard">
        <div class="form-group">
            <label for="cardTextInput">Edit text of the card</label>
            <input type="text" name="cardText" class="form-control" id="cardTextInput" value="<%=currentCard.getResource()%>"}>
            <input type="hidden" name="cardId" value="<%=request.getAttribute("cardId")%>">
<%--            <%String message = (String) request.getAttribute("message");--%>
<%--                if (message == null) {--%>
<%--                    message="";--%>
<%--                }%>--%>
<%--            <p  style="color: red; font-size: 14px">--%>
<%--                <%=message%>--%>
<%--            </p>--%>
        </div>
        <button type="submit" class="btn btn-primary">Edit</button>
    </form>

</body>
</html>
