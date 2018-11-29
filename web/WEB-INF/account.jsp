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
        <h2>Hello, ${username}!</h2>
        <a href="categories">Manage Categories</a><br>
        <a href="users">Manage Users</a><br>
        <a href="inventory">User Inventory</a><br>
        <a href="login?logout">Log out.</a><br><br>
        
        <form method="post">
            <h2> Edit Account Information</h2>
            First Name: <input type="text" name="editfirst" value="${first}"><br>
            Last Name: <input type="text" name="editlast" value="${last}"><br>
            Email: <input type="text" name="editemail" value="${email}"><br>
            Password: <input type="text" name="editpass" value="${password}"><br>
            <input type="submit" value="Change User Information">
            <input type="hidden" name="action" value="edit"><br>
            ${message}
        </form>
        <form method="post">
            <input type="submit" value="Deactivate account">
            <input type="hidden" name="action" value="deactivate"><br>
        </form>
    </body>
</html>
