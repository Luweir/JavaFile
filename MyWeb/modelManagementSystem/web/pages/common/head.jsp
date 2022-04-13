<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/5
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 动态获取url--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>


<%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>


<html>
<head>
    <title>Title</title>
    <!-- base标签固定相对路径跳转的结果，book对应到web那个目录，都从web开始-->
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        $(function (){
            // 点击登出
            $("#logout").click(function () {
                top.location.href = "${basePath}userServlet?action=logout";
            })
        })
    </script>
    <style>
        a {
            text-decoration: none;
            color: #1e1e1e;
        }
    </style>
</head>
<body style="margin: 0;padding: 0">
<div style="height: 50px;display: flex;justify-content: space-between;align-items: center;">
    <div style="margin-left: 30px;display: flex;justify-content: center;align-items: center;">
        <img src="${basePath}static/img/log.png" style="height: 40px;width: 60px;">
        <label style="text-align: center;">模型管理系统</label>
    </div>
    <div style="margin-right: 30px;display: flex;justify-content: center;align-items: center;">
        <div style="margin-right: 30px;" >
            <img src="${basePath}static/img/user.png" style="width: 20px">
            <a href="${basePath}pages/user/personal_center.jsp" target="target">${sessionScope.user.username}</a>
        </div>
        <div id="logout">
            <img src="${basePath}static/img/exit.png" style="width: 20px">
            <a href="">退出系统</a>
        </div>
    </div>
</div>
</body>
</html>
