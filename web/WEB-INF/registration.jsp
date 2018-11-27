<%-- 
    Document   : registration
    Created on : Nov 26, 2018, 2:59:55 PM
    Author     : 587568
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Registration Page</h1>
        <form method="post">
            Username: <input type="text" name="username" value="${username}"><br>
            Password <input type="password" name="password"><br>
            Confirm Password: <input type="password" name="confirmpassword"><br>
            First name: <input type="text" name="firstname" value="${firstname}"><br>
            Last name: <input type="text" name="lastname" value="${lastname}"><br>
            Email: <input type="text" name="email" value="${email}"><br>
            <input type="submit" value="Register"><br>
            ${message}
        </form>
        <br>
        <br>
        
        Already have an account? <a href="login">Log in.</a>
    </body>
</html>
