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
<%--    Form Control
@action: địa chỉ URL nhận dữ liệu form khi nhấp nút submit
@method: hình thức gửi dữ liệu form, có 2 giá trị
     -   GET: Tạo chuỗi truy vấn và đính kèm URL của @action
     -   POST: Mở kênh truyền thông ngầm gửi dữ liệu lên server
--%>
<form action="/ket-qua-login" method="post">
    Username: <input type="text" name="username" placeholder="Enter your username"><br>
    Password: <input type="password" name="password" placeholder="Enter your password"><br>
    <button type="submit">LOGIN</button>
</form>
</body>
</html>
