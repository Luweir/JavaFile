<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>我的申请</title>
    <base href=<%=basePath%>>
    <link rel="stylesheet" type="text/css" href="static/css/css.css"/>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $(".recordDetail").click(function () {
                let recordId = $(this).attr("recordId");
                // 携带记录ID进入记录详情页面
                location.href = "${basePath}recordServlet?action=recordDetail&recordId=" + recordId;
            })
        })
    </script>
</head>
<body>
<div id="main">
    <div class="top_search_bar">
    </div>
    <table>
        <tr>
            <th>
                模型名称
            </th>
            <th>
                发起人
            </th>
            <th>
                申请类型
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
                    <td>${entry.applicantName}</td>
                    <c:if test="${entry.recordType == 'apply'}">
                        <td>模型使用申请</td>
                    </c:if>
                    <c:if test="${entry.recordType == 'release'}">
                        <td>模型发布申请</td>
                    </c:if>
                    <td>${entry.applyDate}</td>
                    <td>${entry.recordState}</td>
                    <td>
                        <c:if test="${entry.recordState == '待审核'}">
                            <input type="button" recordId="${entry.id}" value="查看" disabled="disabled">
                        </c:if>
                        <c:if test="${entry.recordState != '待审核'}">
                            <input type="button" class="recordDetail" recordId="${entry.id}" value="查看">
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${ empty requestScope.records}">
            <tr>
                <td colspan="6">
                    暂未查询到你的申请记录
                </td>
            </tr>
        </c:if>
    </table>
</div>
</body>
</html>
