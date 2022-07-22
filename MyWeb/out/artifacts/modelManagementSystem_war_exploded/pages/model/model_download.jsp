<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/15
  Time: 23:15
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
            // 查看模型详情
            $(".seeModel").click(function () {
                let modelId = $(this).attr("modelId");
                // alert(modelId);
                location.href = "${basePath}recordServlet?action=modelDetailApplyAudit&operationType=detail&modelId=" + modelId;
            })
            // 下载模型
            $(".downloadModel").click(function () {
                let downloadId = $(this).attr("downloadId");
                this.form.action = "${basePath}modelServlet?action=downloadModel&downloadId=" + downloadId;
                this.form.submit();
            })
        })
    </script>
    <link rel="stylesheet" type="text/css" href="static/css/css.css"/>
</head>
<body>
<div id="main">
    <form method="post" action="recordServlet?action=showCanDownload">
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
    <form method="post" action="">
        <table>
            <tr>
                <th>
                    序号
                </th>
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
                    操作
                </th>
            </tr>
            <c:if test="${not empty requestScope.downloads}">
                <%
                    int i = 1;
                    request.setAttribute("i", 1);
                %>
                <c:forEach items="${requestScope.downloads}" var="entry">
                    <tr>
                        <td>${i}</td>
                        <%
                            int temp = (int) request.getAttribute("i");
                            request.setAttribute("i", temp + 1);
                        %>
                        <td>${entry.modelName}</td>
                        <td>${entry.modelType}</td>
                        <td>${entry.releaseDate}</td>
                        <td>
                            <input type="button" value="查看" class="seeModel" modelId="${entry.modelId}">
                            <input type="button" value="下载" class="downloadModel" downloadId="${entry.id}">
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.downloads}">
                <tr>
                    <td colspan="4">暂无可下载模型，请先申请使用模型！</td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>
