<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/12
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 动态获取url--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<html>
<head>
    <title>Title</title>
    <!-- base标签固定相对路径跳转的结果，book对应到web那个目录，都从web开始-->
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        // 权限控制
        $(document).ready(function () {
            let permissions = "${sessionScope.user.permissions}";
            if (permissions === '1') {
                $(".admin").css("pointer-events", "none");
                $(".dev").css("pointer-events", "none");
            }
            if (permissions === '2') {
                $(".admin").css("pointer-events", "none");
            }
        })
        $(function () {
            $("a").click(function () {
                $("a").css("color", "#0a0a0a");
                $(this).css("color", "#53b4e8");
            })
        })
    </script>
    <style>
        a {
            text-decoration: none;
            color: #1e1e1e;
        }

        body {
            background-color: #f5f6f7;
            margin: 0;
            padding: 0;
            width: 100%;
        }

        table {
            width: 180px;
            text-align: left;
            border: #1e1e1e 1px solid;
            margin-top: 20px;
            table-layout: fixed;
            word-break: break-all;
            border-collapse: collapse;
        }

        table th {
            height: 40px;
            display: flex;
            justify-content: start;
            background-color: #d3e6f0;
            align-items: center;
        }

        table tr {
            height: 40px;
        }

        table td {
            padding-left: 10px;
            border: #1e1e1e 1px solid;
        }

        img {
            width: 20px;
            height: 20px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th><img src="static/img/right.png">业务中心</th>
    </tr>
    <tr>
        <td>
            <a target="target" href="recordServlet?action=recordListForWaiting" class="admin">我的待办</a>
        </td>
    </tr>
    <tr>
        <td><a target="target" href="recordServlet?action=recordListForDone" class="admin">我的已办</a></td>
    </tr>

    <tr>
        <td><a target="target" href="recordServlet?action=myApplyRecord">我的申请</a></td>
    </tr>
    <tr>
        <td><a target="target" href="recordServlet?action=myEvaluation">我的评价</a></td>
    </tr>
    <tr>
        <th><img src="static/img/right.png">模型中心</th>
    </tr>
    <tr>
        <td><a target="target" href="modelServlet?action=modelRecommend">模型推荐</a></td>
    </tr>
    <tr>
        <td><a target="target" href="pages/model/model_register.jsp" class="dev">模型注册</a></td>
    </tr>
    <tr>
<%--        <td><a target="target" href="modelServlet?action=modelListForReleased">模型查询</a></td>--%>
        <td><a target="target" href="modelServlet?action=pageForModelQuery">模型查询</a></td>
    </tr>
    <tr>
        <td><a target="target" href="modelServlet?action=modelDirectory&type=notAdmin">模型分类</a></td>
    </tr>
    <tr>
        <td><a target="target" href="pages/model/combination_model_query.jsp">组合模型</a></td>
    </tr>
    <tr>
        <td><a target="target" href="modelServlet?action=modelRelease">模型发布</a></td>
    </tr>
    <tr>
        <td><a target="target" href="recordServlet?action=showCanDownload">模型下载</a></td>
    </tr>
    <tr>
        <th><img src="static/img/right.png">系统管理</th>
    </tr>
    <tr>
        <td><a target="target" href="modelServlet?action=modelManagement" class="admin">模型管理</a></td>
    </tr>
    <tr>
        <td><a target="target" href="modelServlet?action=modelDirectory&type=admin" class="admin">分类管理</a></td>
    </tr>
    <tr>
        <td><a target="target" href="userServlet?action=userManagement" class="admin">用户管理</a></td>
    </tr>
</table>
</body>
</html>
