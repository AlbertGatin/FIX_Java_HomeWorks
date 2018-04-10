<%--
  Created by IntelliJ IDEA.
  User: Альберт
  Date: 09.04.2018
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please add user personal data
    </div>
    <form action="/main" method="post">
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="first-name">First Name
            <input class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <label for="last-name">Last Name
            <input class="input-field" type="text" id="last-name" name="last-name">
        </label>
        <label for="email">Email
            <input class="input-field" type="text" id="email" name="email">
        </label>
        <label for="mobile">Mobile
            <input class="input-field" type="text" id="mobile" name="mobile">
        </label>
        <input type="submit" value="SignUp">
    </form>
</div>
</body>
</html>
