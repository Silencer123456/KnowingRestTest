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
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">


    <title>Report</title>


    <script>
        function myFunction(plot) {
            alert("Hello! I am an alert box!");
            eval(plot);
        }

    </script>
</head>
<body>

<header>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="#">Search</a>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/visualize.jsp">Visualize</a>
    </nav>
</header>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="mt-5">REST API Demonstration</h1>
            <p class="lead">Complete with pre-defined file paths and responsive navigation!</p>
            <ul class="list-unstyled">
            </ul>
            <form action="${pageContext.request.contextPath}/query" method="get">
                <input class="form-control" type="text" placeholder="Search" name="queryText" aria-label="Search">
                <input class="btn btn-primary" type="submit" name="queryButton" value="Submit">
            </form>


        </div>
    </div>

    <button onclick="myFunction(${plot})">Try it</button>

    <div class="list-group">
        <c:choose>
            <c:when test="${not empty docs}">
                <jsp:useBean id="docs" scope="request" type="java.util.List"/>
                <c:forEach var="doc" items="${docs}">
                    <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${doc.getTitle()}</h5>
                            <small>${doc.getYear()}</small>
                        </div>
                        <small>${doc.getNumber()}</small>
                        <p class="mb-1">${doc.getAbstractText()}</p>
                        <small>Authors:</small>
                        <c:forEach var="author" items="${doc.getAuthors()}">
                            <small>${author}, </small>
                        </c:forEach>
                    </a>
                </c:forEach>
            </c:when>
            <c:otherwise>
                No results to display
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
