<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/16/2026
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${tenTruong}</h1>
    <form action="/ket-qua-login" method="post">
        Username: <input type="text" name="username" placeholder="Enter your username"><br>
        Password: <input type="password" name="password" placeholder="Enter your password"><br>
        <button type="submit">LOGIN</button>
    </form>
</body>
</html>
