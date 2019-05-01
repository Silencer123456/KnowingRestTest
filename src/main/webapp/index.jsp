<%--
  Created by IntelliJ IDEA.
  User: kiv
  Date: 27.03.2019
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <title>$Title$</title>
  </head>
  <body>

  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="query.jsp">Search</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/visualize.jsp">Visualize</a>
        </li>
      </ul>
    </div>
  </nav>

  <!-- Full Page Image Header with Vertically Centered Content -->
  <header class="masthead">

    <div class="container h-100">
      <div class="row h-100 align-items-center">
        <div class="col-12 text-center">

            <div class="col-lg-12 text-center">
                <h1 class="mt-5">REST API Demonstration</h1>
                <p class="lead">Testing the Knowing IPR REST API</p>
            </div>

          <form class="card card-sm" action="${pageContext.request.contextPath}/query" method="get">>
            <div class="card-body row no-gutters align-items-center">
              <div class="col-auto">
                <i class="fas fa-search h4 text-body"></i>
              </div>
              <!--end of col-->
              <div class="col">
                <input class="form-control form-control-lg form-control-borderless" type="search" name="queryText" placeholder="Search topics or keywords">
              </div>
              <!--end of col-->
              <div class="col-auto">
                <input class="btn btn-primary" type="submit" value="Submit">
              </div>
              <!--end of col-->
            </div>
          </form>
        </div>
      </div>
    </div>
  </header>
  </body>
</html>
