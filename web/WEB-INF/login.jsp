<%-- 
    Document   : login
    Created on : Nov 7, 2018, 11:03:46 AM
    Author     : 587568
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form method="post" action="login">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="username"><br>
            <input type="submit" value="Log In">
            <br>
            ${message}
        </form>
    </body>
</html>
