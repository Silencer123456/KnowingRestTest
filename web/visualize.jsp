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


    <title>Visualize</title>

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
        <a class="navbar-brand" href="query.jsp">Search</a>
        <a class="navbar-brand" href="#">Visualize</a>
    </nav>
</header>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="mt-5">REST API Demonstration</h1>
            <p class="lead">Complete with pre-defined file paths and responsive navigation!</p>
            <ul class="list-unstyled">
                <li>Bootstrap 4.2.1</li>
                <li>jQuery 3.3.1</li>
            </ul>
            <form action="${pageContext.request.contextPath}/visualize" method="get">
                <input class="form-control" type="text" placeholder="Search" name="queryText" aria-label="Search">
                <input class="btn btn-primary" type="submit" name="visualizeButton" value="Visualize">
            </form>
        </div>
    </div>

    <button onclick="myFunction(${plot})">Try it</button>
</div>
</body>
</html>
