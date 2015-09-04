<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Abram
  Date: 9/1/2015
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../resources/library.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="header">
        <div class="container">
            <a href="/hello"><h1 class="titleGray">Class</h1></a>
            <a href="/hello"><h1 class="titleGreen">Library</h1></a>
            <img class="pull-right img-sm" src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRpyrQsUDS38utZXuhU8B2574sdpoxUyH3YbbJDzSeCE2Xg7tC5"/>
        </div>
    </div>
    <nav class = "navbar navbar-default">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">See all books<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">List books by</li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/allBooksOrdered?keyword=title">Title</a></li>
                            <li><a href="/allBooksOrdered?keyword=author">Author</a></li>
                            <li><a href="/allBooksOrdered?keyword=color">Level Color</a></li>
                            <li><a href="/allBooksOrdered?keyword=availability">Availability</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-nav" role="search" action="/search" method="get">
                    <div class="form-group">
                        <input type="text" class="form-control" name="keyword" placeholder="Keyword">
                    </div>
                    <button type="submit" class="btn btn-default">Search</button>
                </form>
                <ul class="nav navbar-nav">
                    <li><a href="/advancedSearch">Advanced Search</a></li>
                    <li><a href="/add">Add Book</a></li>
                    <li><a href="/remove">Remove Book</a></li>
                    <li><a href="/">Log In/Out</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div class="line"></div>
    <div class="container">
        <table class=" table table-condensed table-hover">
            <thead>
            <tr>
                <td class="text-center"><h3>ID</h3></td>
                <td class="text-center"><h3>Reading Level</h3></td>
                <td class="text-center"><h3>Title</h3></td>
                <td class="text-center"><h3>Author</h3></td>
                <td class="text-center"><h3>Availability</h3></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td class="text-center">${book.id}</td>
                    <td class="text-center" style="background-color: ${book.color}">${book.level}</td>
                    <td class="text-center"><a style="text-decoration: none" href="<c:url value="/book?id=${book.id}"/>">${book.title}</a></td>
                    <td class="text-center">${book.author}</td>
                    <td class="text-center">${book.availability}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

