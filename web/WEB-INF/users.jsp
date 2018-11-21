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

        <a href="categories">Manage Categories</a>
        <a href="inventory">Manage Inventory</a>
        <a href="login?logout">Log out.</a><br>
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
                    <td>
                        <form method="post">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="pull">
                            <input type="hidden" name="selected" value="${user.username}"> 
                        </form>
                    </td>
                    <td>
                        <form method="post">
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selected" value="${user.username}"> 
                        </form>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${!user.active}">
                                <form method="post">
                                    <input type="submit" value="Reactivate">
                                    <input type="hidden" name="action" value="reactivate">
                                    <input type="hidden" name="selected" value="${user.username}"> 
                                </form>
                            </c:when>
                            <c:when test="${user.active}">
                                <form method="post">
                                    <input type="submit" value="Deactivate">
                                    <input type="hidden" name="action" value="deactivate">
                                    <input type="hidden" name="selected" value="${user.username}"> 
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        ${action}

        <c:choose>
            <c:when test="${add == 1}">
                <h3>Add User Information</h3>
            </c:when>
            <c:when test="${add == 0}">
                <h3>Edit User Information</h3>
            </c:when>
        </c:choose>
        <form method="post">
            First Name: <input type="text" name="givenFirst" value="${pulledFirst}"><br>
            Last Name: <input type="text" name="givenLast" value="${pulledLast}"><br>
            Username: <input type="text" name="givenUsername" value="${pulledUsername}"><br>
            Password: <input type="text" name="givenPassword" value="${pulledPassword}"><br>
            Email: <input type="text" name="givenEmail" value="${pulledEmail}"><br>
            <c:choose>
                <c:when test="${add == 1}">
                    <input type="submit" value="Add">
                    <input type="hidden" name="action" value="insert">
                </c:when>
                <c:when test="${add == 0}">
                    <input type="submit" value="Edit">
                    <input type="hidden" name="action" value="update">
                </c:when>
            </c:choose>
        </form>
    </body>
</html>
