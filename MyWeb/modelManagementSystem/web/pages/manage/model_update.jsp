<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/12/2
  Time: 18:53
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
            $("#reset").click(function () {
                let modelId = $(this).attr("modelId");
                location.href = "${basePath}modelServlet?action=updateModel&type=enter&modelId=" + modelId;
            })
            $("#sub").click(function () {
                if (confirm("您确定要模型信息吗")) {
                    let modelId = $(this).attr("modelId");
                    this.form.action = "${basePath}modelServlet?action=updateModel&type=save&modelId=" + modelId;
                    this.form.submit();
                } else {
                    return false;
                }

            })
        })
    </script>
    <style>
        form {
            width: 1000px;
            margin: 0 auto;
        }

        table {
            width: 800px;
            margin: 0 auto;
        }
    </style>
</head>

<body>
<div>
    <form method="post" action="">
        <fieldset>
            <legend>模型基本信息</legend>
            <table>
                <tr>
                    <th>
                        模型名称：
                    </th>
                    <td>
                        <input type="text" name="modelName" value="${requestScope.model.modelName}">
                    </td>
                    <th>
                        模型类型：
                    </th>
                    <td>
                        <select name="modelType" id="modelType">
                            <c:if test="${requestScope.model.modelType == '计算模型'}">
                                <option value="计算模型" selected="selected">计算模型</option>
                                <option value="功能模型">功能模型</option>
                            </c:if>
                            <c:if test="${requestScope.model.modelType == '功能模型'}">
                                <option value="计算模型">计算模型</option>
                                <option value="功能模型" selected="selected">功能模型</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                        开发语言：
                    </th>
                    <td>
                        <input type="text" name="developmentLanguage" value="${requestScope.model.developmentLanguage}">
                    </td>
                    <th>
                        版本号：
                    </th>
                    <td>
                        <input type="text" name="version" value="${requestScope.model.version}">
                    </td>
                </tr>
                <tr>
                    <th>模型简介：</th>
                    <td colspan="3"><textarea cols="80" rows="8" name="modelDescription">${requestScope.model.modelDescription}</textarea></td>
                </tr>
                <tr>
                    <th>
                        应用领域：
                    </th>
                    <td><input type="text" name="applicationField" value="${requestScope.model.applicationField}">
                    </td>
                    <th>
                        模型标签：
                    </th>
                    <td>
                        <input type="text" name="modelTags" value="${requestScope.model.modelTags}">
                    </td>
                </tr>
                <tr>
                    <th>
                        创建单位：
                    </th>
                    <td>
                        <input type="text" name="createDepartment" value="${requestScope.model.createDepartment}">
                    </td>
                    <th>
                        完成单位：
                    </th>
                    <td>
                        <input type="text" name="completionDepartment" value="${requestScope.model.completionDepartment}">
                    </td>
                </tr>
                <tr>
                    <th>
                        模型归类：
                    </th>
                    <td>
                        <select name="modelDirectoryCode">
                            <c:forEach items="${sessionScope.directories}" var="item">
                                <c:if test="${item.curDirectory == requestScope.model.modelDirectoryCode}">
                                    <option value="${requestScope.model.modelDirectoryCode}"
                                            selected="selected">${requestScope.model.modelDirectoryCode}</option>
                                </c:if>
                                <c:if test="${item.curDirectory != requestScope.model.modelDirectoryCode}">
                                    <option value="${item.curDirectory}">${item.curDirectory}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                        运行环境：
                    </th>
                    <td colspan="3">
                        <textarea rows="8" cols="80" name="runtimeEnvironment">${requestScope.model.runtimeEnvironment}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>
                        测试说明：
                    </th>
                    <td colspan="3">
                        <textarea rows="8" cols="80" name="testDescription">${requestScope.model.testDescription}</textarea>
                    </td>
                </tr>
            </table>
            <div style="display: flex;justify-content: center;margin-top: 20px">
                <input type="button" id="reset" modelid="${requestScope.model.id}" value="重置">
                <input type="button" id="sub" modelid="${requestScope.model.id}" style="margin-left: 20px"
                       value="保存">
            </div>
        </fieldset>
    </form>
</div>
</body>

</html>
