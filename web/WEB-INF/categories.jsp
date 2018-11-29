<%-- 
    Document   : categories
    Created on : Nov 15, 2018, 12:52:09 PM
    Author     : 587568
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Categories</title>
    </head>
    <body>
        <h1>Manage Categories</h1>
        <h2>Hello, ${username}!</h2>
        <a href="users">Manage Users</a>
        <a href="inventory">Manage Inventory</a>
        <a href="login?logout">Log out.</a><br>
        <table>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
            </tr>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.categoryID}</td>
                    <td>${category.categoryName}</td>
                    <td>
                        <form method="post">
                            <input type="submit" value="Edit Category Name">
                            <input type="hidden" name="action" value="pullcategory">
                            <input type="hidden" name="selected" value="${category.categoryID}"> 
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:choose>
            <c:when test="${add == 1}">
                <h3>Add Item</h3>
            </c:when>
            <c:when test="${add == 0}">
                <h3>Edit Item</h3>
            </c:when>
        </c:choose>
        <form method="post">
            Category Name: <input type="text" name="addcategory" value="${editcategory}">
            <c:choose>
                <c:when test="${add == 1}">
                    <input type="submit" value ="Add">
                    <input type="hidden" name="action" value="addcategory">
                </c:when>
                <c:when test="${add == 0}">
                    <input type="submit" value ="Edit">
                    <input type="hidden" name="action" value="editcategory">
                </c:when>
            </c:choose>
        </form>
        ${message}
    </body>
</html>
