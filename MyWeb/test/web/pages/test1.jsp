<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/18
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            $("#but").click(function () {
                $.ajax({
                    url:"${basePath}testServlet?action=test",
                    type:"POST",
                    async:false,//取消异步请求
                    contentType:false,
                    processData:false,
                    success:function (data) {

                    }
                })
            })

        })
    </script>
</head>
<body>
    <input type="button" id="but" value="点击">
    <c:forEach begin="1" end="${count}" var="i">
        <div>哈哈哈哈</div>
    </c:forEach>
</body>
</html>