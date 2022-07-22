<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/16
  Time: 14:25
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
    <script>
        $(function () {
            $(".evaluate").click(function () {
                let modelName = $(this).parent().parent().find("td:eq(1)").text();
                let downloadId = $(this).attr("downloadId");
                let score = prompt("请输入您对[" + modelName + "]的评分（0-100）");
                let that = this;
                // 输入评分，点击确定
                if (score) {
                    $.ajax({
                        url: "${basePath}recordServlet?action=submitScore",
                        type: "POST",
                        data: {"downloadId": downloadId, "score": score},
                        dataType: "text",
                        success: function (data) {
                            if (data === "false") {
                                alert("评价失败！请重试")
                            } else {
                                $(that).parent().parent().find("td:eq(4)").text(score);
                                $(that).parent().parent().find("td:eq(5)").val("重新评价");
                                alert("谢谢您的评价！");
                            }
                        },
                    })
                }
            })
        })
    </script>
</head>
<body>
<div id="main">
    <div class="top_search_bar">
    </div>
    <form action="" method="post">
        <table class="model_table">
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
                    我的评价
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
                    <tr class="downloadInfo">
                        <td>${i}</td>
                        <%
                            int temp = (int) request.getAttribute("i");
                            request.setAttribute("i", temp + 1);
                        %>
                        <td>${entry.modelName}</td>
                        <td>${entry.modelType}</td>
                        <td>${entry.releaseDate}</td>
                        <c:if test="${ empty entry.score}">
                            <td>待评价</td>
                            <td>
                                <input type="button" value="评价" class="evaluate" downloadId="${entry.id}"
                                       modelId="${entry.modelId}">
                            </td>
                        </c:if>
                        <c:if test="${not empty entry.score}">
                            <td>${entry.score}</td>
                            <td>
                                <input type="button" value="重新评价" class="evaluate" downloadId="${entry.id}"
                                       modelId="${entry.modelId}" >
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.downloads}">
                <tr>
                    <td colspan="6">暂无已评价或未评价模型，请先申请使用并下载模型！</td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>
