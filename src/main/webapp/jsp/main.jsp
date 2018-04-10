<%--
  Created by IntelliJ IDEA.
  User: Альберт
  Date: 09.04.2018
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Main</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/products" method="post">
    <label for="login">Login
        <input class="input-field" type="text" id="login" name="login">
    </label>
    <label for="password">Password
        <input class="input-field" type="password" id="password" name="password">
    </label>
    <input type="submit" value="SignIn">
</form>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Our products!
    </div>
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Cost</th>
        </tr>
        <c:forEach items="${productsFromServer}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.cost}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
