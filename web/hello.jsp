<%--
  Created by IntelliJ IDEA.
  User: kiv
  Date: 27.03.2019
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">

    <title>Report</title>
</head>
<body>

<div class="container">
    <form action="/query">
        <!-- Search form -->
        <input class="form-control" type="text" placeholder="Search" aria-label="Search">
        <input class="btn btn-primary" type="submit" value="Submit">
    </form>

    <div class="list-group">
        <jsp:useBean id="docs" scope="request" type="java.util.List"/>
        <c:forEach var="doc" items="${docs}">
            <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">${doc.getTitle()}</h5>
                    <small>${doc.getYear()}</small>
                </div>
                <p class="mb-1">${doc.getAbstractText()}</p>
                <small>${doc.getNumber()}</small>
            </a>
        </c:forEach>
    </div>
</div>

</body>
</html>
