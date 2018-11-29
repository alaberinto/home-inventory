<%-- 
    Document   : inventory
    Created on : Nov 15, 2018, 12:28:57 PM
    Author     : 587568
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <h1>Inventory</h1>
        <a href="account">Account</a><br>
        <a href="login?logout">Log out.</a><br>
        <table>
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Category</th>
                <th>Price</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.itemID}</td>
                    <td>${item.itemName}</td>
                    <td>${item.category.categoryName}</td>
                    <td><fmt:formatNumber value = "${item.price}" type = "currency"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
