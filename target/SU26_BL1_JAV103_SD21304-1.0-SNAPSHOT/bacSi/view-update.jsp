<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 6/4/2026
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--copy bên hiển thị--%>
<form action="/bac-si/update" method="post">
    ID: <input type="text" name="id" value="${bacSi.id}"> <br>
    Tên: <input type="text" name="ten" value="${bacSi.ten}"> <br>
    Địa Chỉ: <input type="text" name="diaChi" value="${bacSi.diaChi}"><br>
    Lương: <input type="text" name="luong" value="${bacSi.luong}"><br>
    <label>Phòng Khám</label>
    <select name="idPhongKham">
        <c:forEach items="${listPhongKham}" var="pk" varStatus="i">
            <option value="${pk.id}" label="${pk.ten}" ${bacSi.phongKham.id == pk.id ? "selected":""}></option>
        </c:forEach>
    </select><br>
    <button type="submit">Cập nhật Bác Sĩ </button>
</form>

</body>
</html>
