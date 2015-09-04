<%--
  Created by IntelliJ IDEA.
  User: Abram
  Date: 8/12/2015
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Advanced Search</title>
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
  <h2>Advanced Search</h2>
  <hr>
  <img class="pull-left image-small" src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSN2LlN5foqzDt9nZsapHU7cl4TxHpE0s6iUhmGWWfcdgX2E4Cg"/>
  <div class="form-width pull-right">
    <form class="navbar" action="/advancedSearch" method="post">
      <div class="form-group">
        <label for="bookTitle">Title</label>
        <input type="text" class="form-control" id="bookTitle" name="title" placeholder="title">
      </div>
      <div class="form-group">
        <label for="bookAuthor">Author</label>
        <input type="text" class="form-control" id="bookauthor" name="author" placeholder="author">
      </div>
      <div class="form-group pull-left">
        <label for="color">Reading Level Color</label>
        <input type="text" class="form-control" id="color" name="color" placeholder="color">
      </div>
      <div class="form-group pull-left">
        <label for="level">Reading Level Number</label>
        <input type="text" class="form-control" id="level" name="level" placeholder="number">
      </div>
      <div class="form-group pull-left clear-left">
        <button type="submit" class="btn btn-warning btn-lg">Submit</button>
      </div>
    </form>
  </div>
</div>
</body>
</html>
