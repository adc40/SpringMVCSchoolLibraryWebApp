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
    <h2 class="bookHeader">${book.title}</h2>
    <table class="table table-condensed">
        <thead>
        <tr>
            <td class="text-center"><h4>ID</h4></td>
            <td class="text-center"><h4>Author</h4></td>
            <td class="text-center"><h4>Availability</h4></td>
            <td class="text-center"><h4>Location</h4></td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center">${book.id}</td>
            <td class="text-center">${book.author}</td>
            <td class="text-center">${book.availability}</td>
            <td></td>
            <form action="/availability?id=${book.id}" method="post">
                <td><button class="btn btn-primary pull-right" type="submit">Check In/Out</button></td>
            </form>
        </tr>
        </tbody>
    </table>
    <table class=" table table-condensed">
        <thead>
        <tr>
            <td class="text-center"><h4>Reading Level</h4></td>
            <td class="text-center"><h4>Category</h4></td>
            <td class="text-center"><h4>Year</h4></td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center" style="background-color: ${book.color}">${book.level}</td>
            <td class="text-center">${book.category}</td>
            <td class="text-center">${book.year}</td>
            <td><button type="button" class="btn btn-danger pull-right" data-toggle="modal" data-target="#deleteModal">Delete Book</button></td>
        </tr>
        </tbody>
    </table>
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">${book.title}</h4>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete this book from your database?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    <a href="/delete?id=${book.id}" type="button" class="btn btn-primary">Yes</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
