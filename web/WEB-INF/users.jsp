<%-- 
    Document   : users
    Created on : Nov 15, 2018, 12:31:36 PM
    Author     : 587568
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <a href="login?logout">Log out.</a>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>User Type</th>
                <th>Account Status</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:if test="${user.isAdmin}">
                            Admin
                        </c:if>
                            <c:if test="${!user.isAdmin}">
                            Regular User
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${user.active}">
                            Active
                        </c:if>
                            <c:if test="${!user.active}">
                            Inactive
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
