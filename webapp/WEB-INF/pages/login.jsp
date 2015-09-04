<%--
  Created by IntelliJ IDEA.
  User: Abram
  Date: 8/29/2015
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../resources/library.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="header">
        <div class="container">
            <h1 class="titleGray">Class</h1>
            <h1 class="titleGreen">Library</h1>
            <img class="pull-right img-sm" src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRpyrQsUDS38utZXuhU8B2574sdpoxUyH3YbbJDzSeCE2Xg7tC5"/>
        </div>
    </div>
    <div class="navBar"></div>
    <div class="line"></div>
    <div class="container">
        <div class="login">
            <form action="/login" method="post">
                <div class="form-group">
                    <input class="form-control input-lg" type="text" name="username" placeholder="Username" >
                </div>
                <div class="form-group">
                    <input class="form-control input-lg" type="password" name="password" placeholder="Password" >
                </div>
                <div class="form-group pull-left">
                    <input class="btn btn-lg" type="submit" value="Log In">
                </div>
            </form>
            <a class="right" href="/createUser">create account</a>
            <h4 class="clear warning">${message}</h4>
        </div>
    </div>
</body>
</html>
