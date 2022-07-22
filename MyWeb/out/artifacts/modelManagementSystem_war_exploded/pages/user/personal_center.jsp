<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/12
  Time: 6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
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
            $("#sub").click(function () {
                let password=$("#password").val();
                if(password === "${sessionScope.user.password}")
                {
                    alert("新密码与原密码一致！")
                    return false;
                }
                let rePassword=$("#rePassword").val();
                if(password!==rePassword)
                {
                    alert("两次输入密码不一致，请重新输入！");
                    return false;
                }
                if (confirm("你确定要保存吗？")) {
                    $.ajax({
                        url: "${basePath}userServlet?action=updateUser",
                        type: "POST",
                        data: $('form').serialize(),
                        dataType: "text",
                        success: function (data) {
                            if(data==="true")
                            {
                                alert("修改成功");
                            }
                            else
                            {
                                alert("修改失败");
                            }
                        },
                    })
                }
                else
                {
                    return false;
                }
            })
        })
    </script>
    <style>
        body {
            background-color: #f5f6f7;
            padding: 0;
            margin: 0;
        }

        #top {
            width: 1000px;
            margin: 40px auto;
            background-color: #fff;
        }
        .bottom_button{
            margin: 0px auto;
            padding: 20px;
            display: flex;
            justify-content: center;
        }
        tr {
            height: 40px;

        }
        .td_text {
            padding-left: 30px;
            text-align-last: justify;
            width: 80px;
            padding-right: 30px;
        }
        h2{
            padding: 20px;
            text-align: center;
        }
        table {
            margin: 0 auto;
            text-align: center;
            width:400px;

        }
        #sub{
            width: 100px;
            height: 30px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<div id="top">
    <div>
    <h2>
        个人信息
    </h2>
    </div>
        <div>
            <form method="post" action="">
            <table>
                <tr>
                    <td class="td_text">
                        用户名
                    </td>
                    <td>
                        <input type="text" name="username" id="username" readonly="readonly"
                               value="${sessionScope.user.username}">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        新密码
                    </td>
                    <td>
                       <input type="password" name="password" id="password">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        确认密码
                    </td>
                    <td>
                        <input type="password" name="rePassword" id="rePassword">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        真实姓名
                    </td>
                    <td>
                        <input type="text" name="realName" id="realName" value="${sessionScope.user.realName}">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        权限
                    </td>
                    <td>
                        <c:if test="${sessionScope.user.permissions=='1'}">
                            <input type="text" name="permissions" readonly="readonly"
                                   value="用户">
                        </c:if>
                        <c:if test="${sessionScope.user.permissions=='2'}">
                            <input type="text" name="permissions" readonly="readonly"
                                   value="开发者">
                        </c:if>
                        <c:if test="${sessionScope.user.permissions=='3'}">
                            <input type="text" name="permissions" readonly="readonly"
                                   value="管理员">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        所属部门
                    </td>
                    <td>
                        <input type="text" name="department" value="${sessionScope.user.department}">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        电话
                    </td>
                    <td>
                        <input type="text" name="telephone" value="${sessionScope.user.telephone}">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        邮箱
                    </td>
                    <td>
                        <input type="text" name="email" value="${sessionScope.user.email}">
                    </td>
                </tr>
            </table>
            </form>
        </div>
        <div class="bottom_button">
            <input type="button" id="sub" value="保存">
        </div>
</div>
</body>
</html>
