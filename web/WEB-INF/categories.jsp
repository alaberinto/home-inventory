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
        <a href="login?logout">Log out.</a>
        <table>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
            </tr>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.categoryID}</td>
                    <td>${category.categoryName}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
