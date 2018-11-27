<%-- 
    Document   : account
    Created on : Nov 15, 2018, 12:52:16 PM
    Author     : 587568
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
    </head>
    <body>
        <h1>Manage Account</h1>
        <a href="inventory">User Inventory</a><br>
        <a href="login?logout">Log out.</a><br><br>
        
        <form method="post">
            <h2> Edit Account Information</h2>
            First Name: <input type="text" value="${first}"><br>
            Last Name: <input type="text" value="${last}"><br>
            Email: <input type="text" value="${email}"><br>
            Password: <input type="text" value="${password}"><br>
            <input type="submit" value="Change User Information">
        </form>
    </body>
</html>
