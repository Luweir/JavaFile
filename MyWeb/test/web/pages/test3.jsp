<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/21
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<html>
<head>
    <title>Title</title>
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $("input").click(function () {
                alert($("label.className").text());
            })
        })
    </script>
</head>
<body>
<%--<form action="${basePath}testServlet?action=test3" method="post" enctype="multipart/form-data">--%>
<%--    <input type="checkbox" name="modelTags" value="数据">数据--%>
<%--    <input type="checkbox" name="modelTags" value="算法">算法--%>
<%--    <input type="checkbox" name="modelTags" value="统计">统计--%>
<%--    <input type="checkbox" name="modelTags" value="分析">分析--%>
<%--    <input type="submit" value="提交">--%>
<%--</form>--%>
<label class="className">qdasdsdasd</label>
<input type="button" value="点击">
</body>
</html>
