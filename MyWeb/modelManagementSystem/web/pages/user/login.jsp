<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/11
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 动态获取url--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<html>
<head>
    <title>模型管理系统</title>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <base href=<%=basePath%>>
    <script>
        $(function () {
            // 忘记密码
            $("#forgetPwd").click(function () {
                alert("请联系管理员恢复初始密码！");
                return false;
            })
        })
    </script>
    <style>
        body {
            margin:0;
            padding:0;
            font-family:sans-serif;
            background:url("${basePath}static/img/bgimg1.png");
        }
        .box {
            width:300px;
            padding:40px;
            position:absolute;
            top:50%;
            left:50%;
            transform:translate(-50%,-50%);
            background:#19191900;
            text-align:center;
            box-shadow:20px 25px 45px 15px;
        }
        .box h1 {
            color:white;
            text-transform:uppercase;
            font-weight:500;
        }
        .box input[type = "text"],.box input[type = "password"] {
            /*border:0;
            */
            background:none;
            display:block;
            margin:20px auto;
            text-align:center;
            border:2px solid #3498db;
            font-size: 15px;
            padding:14px 10px;
            width:200px;
            outline:none;
            color:white;
            border-radius:24px;
            transition:0.25s;
        }
        .box input[type = "text"]:focus,.box input[type = "password"]:focus {
            width:280px;
            border-color:#2ecc71;
        }
        .box input[type = "submit"] {
            /* border:0;
            */
            background:none;
            display:block;
            margin:20px auto;
            text-align:center;
            border:2px solid #2ecc71;
            padding:14px 40px;
            outline:none;
            color:white;
            border-radius:24px;
            transition:0.25s;
            cursor:pointer;
        }
        .box input[type = "submit"]:hover {
            background:#2ecc71;
        }
        a{
            margin:0 40px;
            color: white;
            text-decoration: none;
        }
        span {
            margin-left: 150px;
            /*visibility: hidden;*/
            color: #fe3b49;
        }
    </style>
</head>
<body>
<form class="box" action="userServlet?action=login" method="post">
    <h1>模型管理系统</h1>
    <span class="errorMsg">${empty requestScope.msg?"":requestScope.msg}</span>
    <input type="text" name="username" placeholder="Username" value="${requestScope.username}">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="登录">
    <div>
        <a href="${basePath}pages/user/register.jsp">立即注册</a>
        <a href="" id="forgetPwd">忘记密码</a>
    </div>
</form>
</body>
</html>