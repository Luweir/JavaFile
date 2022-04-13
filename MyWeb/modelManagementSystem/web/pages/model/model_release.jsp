<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:58
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
    <link rel="stylesheet" type="text/css" href="static/css/css.css" />
    <script>
        $(document).ready(function () {
            // 权限管理：如果不是管理员，不能点击发布
            let permissions = "${sessionScope.user.permissions}";
            if (permissions !== '3') {
                $(".quickRelease").attr("disabled", "disabled");
            }
        })

        $(function () {
            $(".detail").click(function () {
                let modelId = $(this).attr("modelId");
                // alert(modelId);
                location.href = "${basePath}recordServlet?action=modelDetailApplyAudit&modelId=" + modelId + "&operationType=detail";
            })
            $(".quickRelease").click(function () {
                let modelId = $(this).attr("modelId");
                let that = this;
                $.ajax({
                    url: "${basePath}recordServlet?action=quickRelease",
                    type: "POST",
                    data: {"modelId": modelId},
                    dataType: "text",
                    success: function (data) {
                        if (data === "true") {
                            $(that).attr("disabled", "disabled");
                            $(that).parent().parent().find("td:eq(4)").text("已发布");
                            // 是否需要撤销发布
                        } else {
                            alert("发布失败");
                        }
                    },
                })
            })
        })
    </script>

</head>
<body>
<div id="main">
<!-- 顶部搜索栏 -->
<div class="top_search_bar">
    <form method="post" action="modelServlet?action=modelRelease">
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
                注册日期：
                <input type="date" name="startRegisterDate"> 至 <input type="date" name="endRegisterDime">
            </div>
            <div>
                <input type="submit" value="查询">
                <input type="reset" value="重置">
            </div>
        </div>
    </form>
</div>
<!-- 搜索结果显示框 -->
<div>
    <table>

        <tr>
            <th>
                模型名称
            </th>
            <th>
                模型类型
            </th>
            <th>
                注册人
            </th>
            <th>
                注册日期
            </th>
            <th>
                发布状态
            </th>
            <th>
                操作
            </th>
        </tr>
        <c:forEach items="${requestScope.models}" var="item">
            <tr>
                <td>
                        ${item.modelName}
                </td>
                <td>
                        ${item.modelType}
                </td>
                <td>
                        ${item.registerName}
                </td>
                <td>
                        ${item.registerDate}
                </td>
                <td>
                        ${item.modelState}
                </td>
                <td>
                    <input type="button" class="detail" modelId="${item.id}" value="查看">
                    <c:if test="${item.modelState == '已发布'}">
                        <input type="button" class="quickRelease" disabled="disabled" modelId="${item.id}" value="发布">
                    </c:if>
                    <c:if test="${item.modelState == '未发布'}">
                        <input type="button" class="quickRelease" modelId="${item.id}" value="发布">
                    </c:if>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
</div>
</body>
</html>
