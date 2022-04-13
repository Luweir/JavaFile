<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/16
  Time: 13:43
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

    </script>
    <style>
        h2 {
            width: 100px;
            margin: 20px auto;
        }

        fieldset {
            border-color: rgba(30, 30, 30, 0.2);
        }

        table {
            width: 800px;
            margin: 0 auto;
            padding: 0;
        }

        table th {
            text-align-last: justify;
            width: 90px;
            padding-right: 20px;
        }

        body {
            width: 100%;
            margin: 0;
            padding: 0;
        }

        #main {
            width: 800px;
            margin: 0 auto;
        }

        table tr {
            height: 50px;
        }
    </style>
</head>
<body>
<!-- 只显示模型信息和申请、审核信息 -->
<div id="main">
    <h2>记录详情</h2>
    <fieldset>
        <legend>模型基本信息</legend>
        <table>
            <tr>
                <th>模型名称:</th>
                <td>
                    <label>${requestScope.model.modelName}</label>
                </td>
                <th>模型类型:</th>
                <td>

                    <label>${requestScope.model.modelType}</label>
                </td>
            </tr>
            <tr>
                <th>
                    开发语言:
                </th>
                <td>

                    <label>${requestScope.model.developmentLanguage}</label>
                </td>
                <th>
                    版本号:
                </th>
                <td>
                    <label>${requestScope.model.version}</label>
                </td>
            </tr>
            <tr>
                <th>模型简介:</th>
                <td colspan="3"><textarea rows="8" cols="80" name="modelDescription"
                                          disabled="disabled">${requestScope.model.modelDescription} </textarea></td>
            </tr>
            <tr>
                <th>
                    应用领域:
                </th>
                <td colspan="3">
                    <label>${requestScope.model.applicationField}</label>
                </td>
            </tr>
            <tr>
                <th>
                    模型标签:
                </th>
                <td colspan="3">
                    <label>${requestScope.model.modelTags}</label>
                </td>
            </tr>
            <tr>
                <th>
                    创建单位:
                </th>
                <td>
                    <label>${requestScope.model.createDepartment}</label>
                </td>
                <th>
                    完成单位:
                </th>
                <td>
                    <label>
                        ${requestScope.model.completionDepartment}
                    </label>
                </td>
            </tr>
            <tr>
                <th>
                    完成日期:
                </th>
                <td>
                    <label>${requestScope.model.completionDate}</label>
                </td>
                <th>
                    模型归类:
                </th>
                <td>
                    <label>${requestScope.model.modelDirectoryCode}</label>
                </td>
            </tr>
            <tr>
                <th>运行环境:</th>
                <td colspan="3"><textarea rows="8" cols="80" name="runtimeEnvironment"
                                          disabled="disabled">${requestScope.model.runtimeEnvironment}</textarea>
                </td>
            </tr>
            <tr>
                <th>测试说明:</th>
                <td colspan="3"><textarea rows="8" cols="80" name="testDescription"
                                          disabled="disabled">${requestScope.model.testDescription}</textarea></td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>审核详情</legend>
        <table>
            <tr>
                <th>
                    申请人:
                </th>
                <td>
                    ${requestScope.record.applicantName}
                </td>
                <th>
                    申请类型:
                </th>
                <td>
                    <c:if test="${requestScope.record.recordType=='apply'}">
                        模型使用
                    </c:if>
                    <c:if test="${requestScope.record.recordType=='release'}">
                        模型发布
                    </c:if>
                </td>

            </tr>
            <tr>
                <th>
                    申请时间:
                </th>
                <td>
                    ${requestScope.record.applyDate}
                </td>
                <th>
                    审核时间:
                </th>
                <td>
                    ${requestScope.record.reviewDate}
                </td>
            </tr>
            <tr>
                <th>
                    审核结果:
                </th>
                <td>
                    ${requestScope.record.recordState}
                </td>
            </tr>
            <tr>
                <th>
                    审核意见:
                </th>
                <td colspan="3">
                    <textarea cols="80" rows="8" name="reviewOpinion"
                              readonly="readonly">${requestScope.record.reviewOpinion}</textarea>
                </td>
            </tr>
        </table>
    </fieldset>
</div>
</body>

</html>
