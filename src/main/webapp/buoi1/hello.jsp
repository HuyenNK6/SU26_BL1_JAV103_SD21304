<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 5/14/2026
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>XIN CHÀO CÁC BẠN ${message}!!!!</h1>
<%--Ctrl+ shift+ /--%>
<%--Chỉ thị này giúp module hóa các thành phần giao diện,
 giúp chèn toàn bộ mã JSP của trang sub.jsp tại vị trí đặt chỉ thị @include --%>
<%@include file="sub.jsp" %>
<%@include file="sub.jsp" %>
<%@include file="sub.jsp" %>
</body>
</html>
