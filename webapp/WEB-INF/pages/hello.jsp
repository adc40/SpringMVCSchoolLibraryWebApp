<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
    <link rel="stylesheet" type="text/css" href="../../resources/bookstore.css"/>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
</head>
<body class="background">
    <div class="container">
        <a href="/"><h1 class="pull-left navbar">School Library</h1></a>
        <form class="form-inline pull-right navbar" action="/login" method="get">
            <input type="text" class="btn btn-lg" name="username" placeholder="Username" >
            <input type="password" class="btn btn-lg" name="password" placeholder="Password" >
            <input type="submit" class="btn btn-primary btn-lg" value="Log In">
        </form>
    </div>
    <br>
    <div class="container">
        <img class="image-big pull-right" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfIWj2XafZXK3midGIsqOpvk250m3JoSKf0nVq30wZYvbP2t_sqw"/>
        <form class="pull-left room-top" action="/search" method="get">
            <div class="form-group">
                <input type="text" class="btn btn-lg btn-block" name="keyword" placeholder="Find a book" >
                <input type="submit" class="btn btn-success btn-lg btn-block" value="Search">
            </div>
            <div class="form-group">
            <a class="btn btn-warning btn-lg btn-block" href="/advancedSearch" role="button">Advanced Search</a>
            </div>
            <div class="form-group">
            <a class="btn btn-danger btn-lg btn-block" href="/allBooks" role="button">See All Books</a>
            </div>
        </form>
    </div>
</body>
</html>