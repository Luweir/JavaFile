<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 14:00
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
    <script>
        $(function () {
            // 恢复初始密码
            $(".setInitPwd").click(function () {
                let userId = $(this).attr("userId");
                let userName = $(this).parent().parent().find("td:first").text();
                if (confirm("确定要设置[" + userName + "]的密码为初始密码吗？")) {
                    $.ajax({
                        url: "${basePath}userServlet?action=ajaxUpdateUser",
                        type: "POST",
                        data: {
                            "userId": userId,
                            "initPwd": "123456",// 初始密码123456
                        },
                        dataType: "text",
                        success: function (data) {
                            if (data === "true") {
                                alert("恢复初始密码成功！");
                            } else {
                                alert("恢复初始密码失败!");
                            }
                        }
                    })
                }
            })
            // 删除用户
            $(".delete").click(function () {
                let userId = $(this).attr("userId");
                alert(userId);
                let that = this;
                $.ajax({
                    url: "${basePath}userServlet?action=deleteUser",
                    type: "POST",
                    data: {"userId": userId},
                    dataType: "text",
                    success: function (data) {
                        if (data === "true") {
                            $(that).parent().parent().remove();
                        } else {
                            alert("删除失败");
                        }
                    },
                })
            })
            // 更新/保存用户数据
            $(".update").click(function () {
                let opera = $(this).val();
                if (opera === '修改') {
                    $(this).parent().parent().find("input[type='text']").removeAttr("disabled");
                    $(this).val("保存")
                    $(this).parent().parent().find("select").removeAttr("disabled");
                } else {
                    // 1、获得数据
                    let userId = $(this).attr("userId");
                    let realName = $(this).parent().parent().find("input:first").val();
                    let permissions = $(this).parent().parent().find("option:selected").val();
                    let department = $(this).parent().parent().find("input:eq(1)").val();
                    let that = this;
                    // 2、传数据给后台
                    $.ajax({
                        url: "${basePath}userServlet?action=ajaxUpdateUser",
                        type: "POST",
                        data: {
                            "userId": userId,
                            "realName": realName,
                            "permissions": permissions,
                            "department": department
                        },
                        dataType: "text",
                        success: function (data) {
                            if (data === "true") {
                                $(that).parent().parent().find("input:first").val(realName);
                                $(that).parent().parent().find("input:eq(1)").val(department);
                                switch (permissions) {
                                    case "1":
                                        $(that).parent().parent().find("option:first").attr("selected", "selected");
                                        break;
                                    case "2":
                                        $(that).parent().parent().find("option:eq(1)").attr("selected", "selected");
                                        break;
                                    case "3":
                                        $(that).parent().parent().find("option:eq(2)").attr("selected", "selected");
                                }
                                // 3、输入框加入disabled属性
                                $(that).parent().parent().find("input[type='text']").attr("disabled", "disabled");
                                $(that).val("修改")
                                $(that).parent().parent().find("select").attr("disabled", "disabled");
                            } else {
                                alert("修改失败");
                            }
                        },
                    })

                }

            })

        })
    </script>
    <link rel="stylesheet" type="text/css" href="static/css/css.css"/>
</head>
<body>
<div id="main">


    <!-- 顶部搜索栏 -->
    <form method="post" action="userServlet?action=userManagement">
        <div class="top_search_bar">
            <div>
                用户名:
                <input type="text" name="username">
            </div>

            <div>
                真实姓名:
                <input type="text" name="realName">
            </div>
            <div>
                部门:
                <input type="text" name="department">
            </div>
            <div>
                权限类型:
                <select name="permissions">
                    <option value=""> 请选择</option>
                    <option value="1">用户</option>
                    <option value="2">开发者</option>
                    <option value="3">管理员</option>
                </select>
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
                    用户名
                </th>
                <th>
                    真实姓名
                </th>
                <th>
                    权限
                </th>
                <th>
                    所属部门
                </th>
                <th>
                    操作
                </th>
            </tr>
            <c:if test="${not empty requestScope.users}">
                <c:forEach items="${requestScope.users}" var="entry">
                    <tr>
                        <td>
                                ${entry.username}
                        </td>
                        <td>
                            <input type="text" value="${entry.realName}" disabled="disabled">
                        </td>

                        <td>
                            <select disabled="disabled">
                                <c:if test="${empty entry.permissions}">
                                    <option value="1">用户</option>
                                    <option value="2">开发者</option>
                                    <option value="3">管理员</option>
                                </c:if>
                                <c:if test="${entry.permissions==1}">
                                    <option selected="selected" value="1">用户</option>
                                    <option value="2">开发者</option>
                                    <option value="3">管理员</option>
                                </c:if>
                                <c:if test="${entry.permissions==2}">
                                    <option value="1">用户</option>
                                    <option selected="selected" value="2">开发者</option>
                                    <option value="3">管理员</option>
                                </c:if>
                                <c:if test="${entry.permissions==3}">
                                    <option value="1">用户</option>
                                    <option value="2">开发者</option>
                                    <option selected="selected" value="3">管理员</option>
                                </c:if>
                            </select>
                        </td>
                        <td>
                            <input type="text" disabled="disabled" value="${entry.department}"></td>
                        <td>
                            <input type="button" class="update" userId="${entry.id}" value="修改">
                            <input type="button" class="delete" userId="${entry.id}" value="删除">
                            <input type="button" class="setInitPwd" userId="${entry.id}" value="恢复初始密码">
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.users}">
                <tr>
                        <%--虽然不可能，但也写下--%>
                    <td colspan="4">暂无已注册用户</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>
