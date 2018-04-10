<%--
  Created by IntelliJ IDEA.
  User: Альберт
  Date: 09.04.2018
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
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
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add your products
    </div>
    <form method="post" action="/products">
        <label for="name">Name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="desc">Description
            <input class="input-field" type="text" id="desc" name="desc">
        </label>
        <label for="cost">Cost
            <input class="input-field" type="text" id="cost" name="cost">
        </label>
        <input type="submit" value="Add product">
    </form>
</div>
</body>
</html>
