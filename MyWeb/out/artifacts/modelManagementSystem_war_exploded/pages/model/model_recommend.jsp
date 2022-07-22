<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/14
  Time: 8:01
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
    <link rel="stylesheet" type="text/css" href="static/css/css.css"/>
</head>
<body>
<!-- 顶部搜索栏 -->
<div id="main">
    <div class="top_search_bar">

    </div>
    <table>
        <tr>
            <th>
                模型名称
            </th>
            <th>
                模型类型
            </th>
            <th>
                发布日期
            </th>
            <th>
                下载量
            </th>
            <th>
                评分
            </th>
            <th>
                操作
            </th>
        </tr>
        <c:if test="${not empty requestScope.models}">
            <c:forEach items="${requestScope.models}" var="entry">
                <tr>
                    <td>${entry.modelName}</td>
                    <td>${entry.modelType}</td>
                    <td>${entry.releaseDate}</td>
                    <td>${entry.downloads}</td>
                    <td>${entry.score}</td>
                    <td>
                        <input type="button" value="查看" class="seeModel" modelId="${entry.id}">
                        <input type="button" value="申请" class="applyModel" modelId="${entry.id}">
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.models}">
            <tr>
                <td colspan="4">暂无已发布模型，请开发人员先注册模型</td>
            </tr>
        </c:if>
    </table>

</div>
</body>
</html>
