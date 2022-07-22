<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/18
  Time: 17:10
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
</head>
<body>
<table>
    <c:forEach items="${methods}" var="item">
        <tr>
            <td>
                xxxx
            </td>
            <td>
                方法名：${item.key.methodName}
            </td>
            <td>
                返回值类型：${item.key.returnType}
            </td>
            <td>参数个数：${item.key.parameterCount}</td>
        </tr>
        <%
            request.setAttribute("i", 1);
        %>
        <c:forEach items="${item.value}" var="it">
            <tr>
                <td>
                    参数序号：${i}
                </td>
                <%
                    int i = (int) request.getAttribute("i");
                    request.setAttribute("i", i + 1);
                %>
                <td>
                    参数名：${it.paramName}
                </td>
                <td>
                    参数类型：${it.paramType}
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>
