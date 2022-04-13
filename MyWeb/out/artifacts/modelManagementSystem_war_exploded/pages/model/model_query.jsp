<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--动态获取url--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>Title</title>
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            // 页面加载完成后
            // 点击查看按钮
            $(".seeModel").click(function () {
                let modelId = $(this).attr("modelId");
                location.href = "${basePath}recordServlet?action=modelDetailApplyAudit&operationType=detail&modelId=" + modelId;
            })
            // 点击申请按钮
            $(".applyModel").click(function () {
                let modelId = $(this).attr("modelId");
                // alert(modelId);
                location.href = "${basePath}recordServlet?action=modelDetailApplyAudit&operationType=apply&modelId=" + modelId;
            })
        })
    </script>
    <link rel="stylesheet" type="text/css" href="static/css/css.css"/>
</head>
<body>
<!-- 顶部搜索栏 -->
<div id="main">
    <form method="post" action="modelServlet?action=screeningModel&back=model_query">
        <div class="top_search_bar">
            <div>
                模型名称：
                <input type="text" name="modelName">
            </div>
            <div>
                模型类型：
                <select name="modelType">
                    <option value="">请选择</option>
                    <option value="计算模型">计算模型</option>
                    <option value="功能模型">功能模型</option>
                </select>
            </div>
            <div>
                关键词：
                <input type="text" name="keyWord" id="key">
            </div>
            <div>
                发布日期：
                <input type="date" name="startReleaseDate"> 至 <input type="date" name="endReleaseDime">
            </div>
            <div>
                <input type="submit" value="查询">
                <input type="reset" value="重置">
            </div>
        </div>
    </form>
    <!-- 搜索结果显示框 -->

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
        <c:if test="${not empty requestScope.page.items}">
            <c:forEach items="${requestScope.page.items}" var="entry">
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
        <c:if test="${empty requestScope.page.items}">
            <tr>
                <td colspan="4">暂无已发布模型，请开发人员先注册模型</td>
            </tr>
        </c:if>

    </table>
    <%--分页导航栏--%>
    <%@ include file="/pages/common/page_nav.jsp" %>
</div>
</body>

</html>
