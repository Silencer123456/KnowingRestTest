<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <title>Report</title>

</head>
<body>

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/visualize.jsp">Visualize</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="mt-5">REST API Demonstration</h1>
            <p class="lead">Testing the REST API</p>
            <ul class="list-unstyled">
            </ul>
            <form action="${pageContext.request.contextPath}/query" method="get">
                <input class="form-control" type="text" placeholder="Search" name="queryText" aria-label="Search">
                <input class="btn btn-primary" type="submit" name="queryButton" value="Submit">
            </form>
        </div>
    </div>

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
