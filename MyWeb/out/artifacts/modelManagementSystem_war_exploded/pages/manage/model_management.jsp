<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:59
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
            $(".update").click(function () {
                let modelId = $(this).attr("modelId");
                location.href = "${basePath}modelServlet?action=updateModel&type=enter&modelId=" + modelId;
            })

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
            $(".abandon").click(function () {
                let modelId = $(this).attr("modelId");
                let that = this;
                let operation = $(this).val();
                $.ajax({
                    url: "${basePath}modelServlet?action=modelAbandon",
                    type: "POST",
                    data: {"modelId": modelId, "operation": operation},
                    dataType: "text",
                    success: function (data) {
                        if (data === "false") {
                            alert("模型[" + operation + "]失败");
                        } else {
                            $(that).val(data);
                        }
                    }
                })
            })
            $(".delete").click(function () {
                let methodName = $(this).parent().parent().find("td:first").text();
                if (confirm("你确定要删除[" + methodName + "]吗?")) {
                    let modelId = $(this).attr("modelId");
                    let that = this;
                    $.ajax({
                        url: "${basePath}modelServlet?action=deleteModel",
                        type: "POST",
                        data: {"modelId": modelId},
                        dataType: "text",
                        success: function (data) {
                            if (data === "false") {
                                alert("删除失败");
                            } else {
                                $(that).parent().parent().remove();
                            }
                        }
                    })
                }
                return false;

            })

        })
    </script>
    <link rel="stylesheet" type="text/css" href="static/css/css.css"/>
</head>
<body>
<!-- 顶部搜索栏 -->
<div id="main">
    <form action="modelServlet?action=screeningModel&back=model_management" method="post">
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
                所属目录：
                <select name="modelDirectoryCode">
                    <option value="">请选择</option>
                    <c:forEach items="${sessionScope.directories}" var="item">
                        <option value="${item.curDirectory}">${item.curDirectory}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                关键词：
                <input type="text" name="keyWord">
            </div>
            <div>
                模型状态：
                <select name="modelState">
                    <option value="">请选择</option>
                    <option value="未发布">未发布</option>
                    <option value="已发布">已发布</option>
                    <option value="拒绝发布">拒绝发布</option>
                    <option value="已弃用">已弃用</option>
                </select>
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
                    所属目录
                </th>
                <th>
                    注册日期
                </th>
                <th>
                    注册人
                </th>
                <th>
                    发布日期
                </th>
                <th>
                    模型状态
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
                        <td>${entry.modelDirectoryCode}</td>
                        <td>${entry.registerDate}</td>
                        <td>${entry.registerName}</td>
                        <td>${entry.releaseDate}</td>
                        <td>${entry.modelState}</td>
                        <td>${entry.downloads}</td>
                        <td>${entry.score}</td>
                        <td>
                            <input type="button" class="detail" modelId="${entry.id}" value="查看">
                            <input type="button" class="update" modelId="${entry.id}" value="修改">
                            <c:if test="${entry.modelState == '未发布'}">
                                <input type="button" class="quickRelease" modelId="${entry.id}" value="发布">
                            </c:if>
                            <c:if test="${entry.modelState == '已发布'}">
                                <input type="button" class="quickRelease" disabled="disabled" modelId="${entry.id}"
                                       value="发布">
                            </c:if>
                            <c:if test="${entry.modelState != '已弃用'}">
                                <input type="button" class="abandon" modelId="${entry.id}" value="废止">
                            </c:if>
                            <c:if test="${entry.modelState == '已弃用'}">
                                <input type="button" class="abandon" modelId="${entry.id}" value="恢复">
                            </c:if>
                            <input type="button" class="delete" modelId="${entry.id}" value="删除">
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.models}">
                <tr>
                    <td colspan="9">暂未查询到模型！</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>
