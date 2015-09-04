<%--
  Created by IntelliJ IDEA.
  User: Abram
  Date: 8/15/2015
  Time: 1:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book Removed</title>
  <link rel="stylesheet" type="text/css" href="../../resources/kidsLibrary.css"/>
  <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
  <a href="/"><h1 class="pull-left navbar">School Library</h1></a>
  <a class="pull-right navbar btn btn-primary btn-lg" href="/allBooks" role="button">See All Books</a>
  <a class="pull-right navbar btn btn-success btn-lg" href="/advancedSearch" role="button">Advanced Search</a>
  <form class="pull-right form-inline" action="/search" method="get">
    <input type="text" class="navbar btn btn-lg" name="keyword" placeholder="Search catalog" >
    <input type="submit" class="navbar btn btn-warning btn-lg" value="Search">
  </form>
</div>
<br>
<div class="container">
  <h3 class="center">Book removed from database</h3>
</div>
</body>
</html>
