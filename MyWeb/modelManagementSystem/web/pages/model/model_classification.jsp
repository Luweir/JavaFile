<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/12/1
  Time: 14:32
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
    <link rel="stylesheet" type="text/css" href="static/css/SimpleTree.css"/>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="static/script/SimpleTree.js"></script>
    <script>
        $(function () {

            // 初始化目录树
            $(".st_tree").SimpleTree({});

            $('body').on('click', '.modelDetail', function () {
                // alert('事件响应');
                let modelId = $(this).attr("modelId");
                location.href = "${basePath}recordServlet?action=modelDetailApplyAudit&operationType=detail&modelId=" + modelId;
            })
            // 单击叶子目录
            $("a[haschild='false']").click(function () {
                let modelDirectoryCode = $(this).text();
                // 通过ajax传递给后端，获取数据后动态生成table的tr
                $.ajax({
                    url: "${basePath}modelServlet?action=modelClassify",
                    type: "POST",
                    data: {
                        "modelDirectoryCode": modelDirectoryCode
                    },
                    dataType: "json",
                    success: function (data) {
                        // alert("success");
                        $(".modelItem").remove();
                        for (let i = 0; i < data.length; i++) {
                            let model = data[i];
                            // alert(model.modelName);
                            $("#modelDown").before(
                                "<tr class='modelItem'>" +
                                "<td>" + model.modelName +
                                "<td>" + model.modelDirectoryCode +
                                "<td>" + model.releaseDate +
                                "<td> <input type='button' class='modelDetail' value='查看' modelid=" + model.id + ">" +
                                "</tr>"
                            )
                        }
                        // 如果没查询到
                        if (data.length === 0) {
                            $("#modelDown").before(
                                "<tr class='modelItem'> <td colspan='4'>暂未查询到该分类下的模型数据</td> </tr>"
                            );
                        }
                    },
                })
            })
        });
    </script>
    <style>
        table {
            width: 1000px;
            margin: 0 auto;
            table-layout: fixed;
            word-break: break-all;
            border-collapse: collapse;
        }

        table tr {
            height: 40px;
        }

        table th {
            font-size: 18px;
            background-color: #f1f5f9;
            border: 1px #d2d4d6 solid;
        }

        table td {
            text-align: center;
            border: 1px #d2d4d6 solid;
        }

        img {
            width: 15px;
            height: 15px;
        }
    </style>
</head>

<body>
<div>
    <div class="st_tree">
        <p>模型分类树</p>
        <ul>
            <li><a href="javascript:void(0);">计算模型</a></li>
            <ul>
                <c:forEach items="${requestScope.calculationModelDirectory}" var="item">
                    <li><a href="javascript:void(0);">${item.key}</a></li>
                    <c:if test="${not empty item.value}">
                        <ul>
                            <c:forEach items="${item.value}" var="entry">
                                <li><a href="javascript:void(0);">${entry}</a></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:forEach>
                <%--                <li><a href="javascript:void(0);">JTBD</a></li>--%>
                <%--                <li><a href="javascript:void(0);">QTBD</a></li>--%>
                <%--                <li><a href="javascript:void(0);">HKBBD</a></li>--%>
                <%--                <li><a href="javascript:void(0);">HJLZD</a></li>--%>
                <%--                <li><a href="javascript:void(0);">HJAFBD</a></li>--%>
            </ul>
            <li><a href="javascript:void(0);">功能模型</a></li>
            <ul>
                <c:forEach items="${requestScope.functionModelDirectory}" var="item">
                    <li><a href="javascript:void(0);">${item.key}</a></li>
                    <c:if test="${not empty item.value}">
                        <ul>
                            <c:forEach items="${item.value}" var="entry">
                                <li><a href="javascript:void(0);">${entry}</a></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:forEach>
            </ul>
            <%--            <ul>--%>
            <%--                <li><a href="javascript:void(0);">GNMX1</a></li>--%>
            <%--                <li><a href="javascript:void(0);">GNMX2</a></li>--%>
            <%--                <li><a href="javascript:void(0);">GNMX3</a></li>--%>
            <%--                <ul>--%>
            <%--                    <li><a href="javascript:void(0);">GNMX3.1</a></li>--%>
            <%--                    <li><a href="javascript:void(0);">GNMX3.2</a></li>--%>
            <%--                </ul>--%>
            <%--            </ul>--%>

    </div>
    <div style="float:left;margin-top: 50px">
        <table id="modelList">
            <tr id="modelHead">
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

            <input type="hidden" id="modelDown">
        </table>
    </div>
</div>
</body>
</html>
