<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
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
            // 携带记录ID跳转到模型审核和模型发布审核页面
            $(".audit").click(function () {
                let recordId = $(this).attr("recordId");
                let modelId = $(this).attr("modelId")
                location.href = "${basePath}recordServlet?action=modelDetailApplyAudit&operationType=audit&modelId=" + modelId + "&recordId=" + recordId;
            })
        })
    </script>
    <link rel="stylesheet" type="text/css" href="static/css/css.css" />
</head>
<body>
<div id="main">
    <form method="post" action="recordServlet?action=recordListForWaiting">
        <div class="top_search_bar">
            <div>
                模型名称：
                <input type="text" name="modelName">
            </div>
            <div>
                发起日期：
                <input type="date" name="startApplyDate"> 至 <input type="date" name="endApplyDate">
            </div>

            <div>
                <input type="submit" value="查询">
                <input type="reset" value="重置">
            </div>
        </div>
    </form>
<table>
    <tr>
        <th>
            模型名称
        </th>
        <th>
            业务类型
        </th>
        <th>
            发起人
        </th>
        <th>
            发起时间
        </th>
        <th>
            状态
        </th>
        <th>
            操作
        </th>
    </tr>
    <c:if test="${not empty requestScope.records}">
        <c:forEach items="${requestScope.records}" var="entry">
            <tr>
                <td>${entry.modelName}</td>
                <c:if test="${entry.recordType=='apply'}">
                    <td>
                        使用申请
                    </td>
                </c:if>
                <c:if test="${entry.recordType=='release'}">
                    <td>
                        发布申请
                    </td>
                </c:if>
                <td>${entry.applicantName}</td>
                <td>${entry.applyDate}</td>
                <td>${entry.recordState}</td>
                <td>
                    <input type="button" name="audit" class="audit" modelId="${entry.modelId}" recordId="${entry.id}"
                           value="审核">
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty requestScope.records}">
        <tr>
            <td colspan="6">暂未查询到你的待办事项</td>
        </tr>
    </c:if>
</table>
</div>
</body>
</html>
