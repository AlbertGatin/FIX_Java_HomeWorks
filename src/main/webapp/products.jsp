<%@ page import="ru.ivmiit.homework.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Альберт
  Date: 13.03.2018
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductsList</title>
    <% ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Компуктер", 25000.00));
        products.add(new Product("Телефун", 5000.00));
        products.add(new Product("Айрподс", 1000.00));
        products.add(new Product("Нубук", 30000.00));
        products.add(new Product("Принтер", 10000.00));%>
</head>
<body>
    <h1>Список товаров</h1>
    <table>
        <% for (Product product:products) {
        %>
        <tr>
            <td><%=product.getName()%></td>
            <td><%=product.getCost()%></td>
        </tr>
        <%}%>
    </table>
</body>
</html>
