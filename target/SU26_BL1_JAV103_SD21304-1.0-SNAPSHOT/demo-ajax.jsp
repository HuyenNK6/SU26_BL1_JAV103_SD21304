<%--
  Created by IntelliJ IDEA.
  User: Huyen
  Date: 6/9/2026
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        function getData(){
            $.ajax({
                url: "/api/ajax/giang-vien",
                type: "get",
                dataType: "json",
                success: function (response){
                    console.log(response)
                },
                error: function (){
                    console.log("LOI ROI!!!!!!")
                }
            })
        }
    </script>
</head>
<body>
    <h1>DEMO AJAX</h1>
    <button onclick="getData()">GET DATA</button>
</body>
</html>
