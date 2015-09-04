<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Abram
  Date: 8/14/2015
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>book</title>
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
  <a href="/delete?id=${book.id}" class="btn btn-danger">Delete Book</a>
  <table class=" table table-condensed table-hover">
    <thead>
    <tr>
      <td><h3>ID</h3></td>
      <td><h3>Reading Level</h3></td>
      <td><h3>Title</h3></td>
      <td><h3>Author</h3></td>
      <td><h3>Availability</h3></td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>${book.id}</td>
      <td style="background-color: ${book.color}">${book.level}</td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.availability}</td>
      <form action="/availability?id=${book.id}" method="post">
        <td><button class="btn btn-success" type="submit">Check In/Out</button></td>
      </form>
    </tr>
    </tbody>
  </table>
</div>
</body>
</html>
