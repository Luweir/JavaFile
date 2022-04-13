<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/14
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<%
    pageContext.setAttribute("parameterCount", 0);
%>
<html>
<head>
    <title>Title</title>
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $("#modelFile").change(function () {
                let files = $(this).prop("files");
                let formData = new FormData;
                formData.append('modelFile', files[0]);
                $.ajax({
                    url: "${basePath}testServlet?action=parseJarFile",
                    type: "POST",
                    data: formData,
                    /**
                     *必须false才会自动加上正确的Content-Type
                     */
                    contentType: false,
                    /**
                     * 必须false才会避开jQuery对 formdata 的默认处理
                     * XMLHttpRequest会对 formdata 进行正确的处理
                     */
                    processData: false,
                    success: function (data) {
                        if (data.status == "true") {
                            alert("上传成功！");
                        }
                        if (data.status == "error") {
                            // alert(data.msg);
                        }
                    },
                    error: function () {
                        alert("上传失败！");
                    }
                });
            });
            $("#click_ope").click(function () {
                $("#xx").remove();
            })
        })
    </script>
    <style>
        table {
            width: 800px;
            margin: 0 auto;
        }
    </style>
</head>

<body>
<form action="testServlet?action=test4" method="post">
    <table>
        <tr>
            <td>
                上传文件
            </td>
            <td>
                <input type="file" name="modelFile" id="modelFile">
            </td>
        </tr>
        <!-- 参数信息 -->
        <tr id="param_info" bgcolor="aqua">
            <td>
                参数名
                <input type="text" name="modelName" required>
            </td>
            <td>
                参数类型
            </td>
            <td>
                参数示例
            </td>
            <td>
                参数描述
            </td>
        </tr>
        <tr id="last_tr">
        </tr>
        <tr>
            <td><input type="button" id="click_ope" value="点击"></td>
        </tr>
    </table>
</form>
</body>
</html>
