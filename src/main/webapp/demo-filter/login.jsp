<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 6/9/2026
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>FORM LOGIN</h1>
<form action="/demo-filter/login" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="text" name="password"><br>
    <button type="submit">LOGIN</button>
</form>
${messageLogin}
</body>
</html>
