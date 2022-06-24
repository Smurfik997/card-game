<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="bootstrap.html" %>
    <title>Rating</title>


</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid w-75" style="height: 2.5rem;">
            <a id="back_btn"class="btn btn-primary" href="/main">Back</a>
        </div>
    </nav>

    <h1>Users rating</h1>

    <ul class="list-group mt-3">
        <c:forEach items="${users}" var="user">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                    ${user.getLogin()}
                <span class="badge bg-primary rounded-pill">${user.getRate()}</span>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
