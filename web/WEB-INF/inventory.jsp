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
                    <td>
                        <form method="post">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="pull">
                            <input type="hidden" name="selected" value="${item.itemID}"> 
                        </form>
                    </td>
                    <td>
                        <form method="post">
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selected" value="${item.itemID}"> 
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        ${action}
        
        <h3>Add Item</h3>
         <form method="post">
             Category:
             <select name="category" value="${category}">
                 <c:forEach var="category" items="${categories}">
                    <option name="category" value="${category.categoryName}">${category.categoryName}</option>
                </c:forEach>
             </select>
             <br>
            Item Name: <input type="text" name="itemname"><br>
            Price: <input type="text" name="itemprice"><br>
            <input type="submit" value="Add Item">
            <input type="hidden" name="action" value="additem">
         </form>
    </body>
</html>
