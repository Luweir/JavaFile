<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/16
  Time: 0:24
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
            /*点击可行性测试*/
            $("input.test").click(function () {
                // alert("进入test");
                let methodCallName = $(this).attr("method");
                let filePath = "${requestScope.filePath}";
                let className = $(this).parents(".methodInfo").find("label.className").text();
                let paramSamples = [];
                let paramTypes = [];
                $(this).parents(".methodInfo").find("label.paramType").each(function () {
                    // alert($(this).text());
                    paramTypes.push($(this).text());
                })
                $(this).parents(".methodInfo").find("input.paramSample").each(function () {
                    // alert($(this).val());
                    paramSamples.push($(this).val());
                })
                let that = this;
                $.ajax({
                    type: "post",
                    url: "${basePath}modelServlet?action=testMethod",
                    data: {
                        "methodCallName": methodCallName,
                        "className": className,
                        "paramSamples": paramSamples,
                        "paramTypes": paramTypes,
                        "filePath": filePath
                    },
                    traditional: true,
                    dataType: "json",
                    success: function (data) {
                        // 将返回值送到实际返回值里面
                        // alert("data:" + data.result);
                        $(that).parents(".methodInfo").find(".realResult").val(data.result);
                    }
                })
            })
            /*唯一性核验*/
            $("input.verify").click(function () {
                let methodCallName = $(this).attr("method");
                // 检查是否存在同名同功能方法
                $.ajax({
                    type: "post",
                    url: "${basePath}methodServlet?action=verifyMethod",
                    data: {
                        "methodCallName": methodCallName,
                    },
                    traditional: true,
                    dataType: "text",
                    success: function (data) {
                        // alert(data)
                        if (data === "true") {
                            // $(that).parent().find("span.verifyMsg").text("通过")
                            // $(that).parent().find("span.verifyMsg").css("color","#40aa96");
                            alert("唯一性验证通过!");
                        } else {
                            // $(that).parent().find("span.verifyMsg").text("未通过")
                            // $(that).parent().find("span.verifyMsg").css("color","#f4533d");
                            alert("唯一性验证不通过！");
                        }

                    }
                })
            })
            // 点击同意
            $("#agree").click(function () {
                this.form.action = "recordServlet?action=updateRecord&recordId=${requestScope.record.id}&recordState=agree";
                this.form.submit();
            })
            // 点击拒绝
            $("#refuse").click(function () {
                this.form.action = "recordServlet?action=updateRecord&recordId=${requestScope.record.id}&recordState=refuse";
                this.form.submit();
            })
            // 点击申请
            $("#apply").click(function () {
                this.form.action = "recordServlet?action=modelApply&modelId=${requestScope.model.id}";
                this.form.submit();
                alert("申请成功，管理员审核通过后方可下载模型!");
            })
        })
    </script>
    <style>
        body {
            margin: 0 auto;
        }

        h2 {
            width: 100px;
            margin: 20px auto;
        }

        form {
            width: 1000px;
            margin: 0 auto;
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

        fieldset {
            border-color: rgba(30, 30, 30, 0.2);
        }

        .paramInfo {
            text-align: center;
        }

        table tr {
            height: 50px;
        }

        fieldset {
            border-color: rgba(30, 30, 30, 0.2);
        }

        textarea {
            resize: none;
        }

        .bottom_button {
            display: flex;
            justify-content: space-evenly;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.operationType=='detail'}">
        <h2>模型详情</h2>
    </c:when>
    <c:when test="${requestScope.operationType=='apply'}">
        <h2>模型申请</h2>
    </c:when>
    <c:otherwise>
        <h2>模型审核</h2>
    </c:otherwise>
</c:choose>
<form method="post">
    <fieldset>
        <%--模型基本信息，这些都会有--%>
        <legend>模型基本信息</legend>
        <table>
            <tr>
                <th>模型名称:</th>
                <td>
                    <%--                    <input type="text" name="modelName" size="20" id="modelName" value="${requestScope.model.modelName}"--%>
                    <%--                           disabled="disabled"/>--%>
                    <label>${requestScope.model.modelName}</label>
                </td>
                <th>模型类型:</th>
                <td>
                    <%--                    <input type="text" value="${requestScope.model.modelType}" disabled="disabled">--%>
                    <label>${requestScope.model.modelType}</label>
                </td>
            </tr>
            <tr>
                <th>
                    开发语言:
                </th>
                <td>
                    <%--                    <input type="text" value="${requestScope.model.developmentLanguage}" disabled="disabled">--%>
                    <label>${requestScope.model.developmentLanguage}</label>
                </td>
                <th>
                    版本号:
                </th>
                <td>
                    <%--                    <input type="text" value="${requestScope.model.version}" disabled="disabled">--%>
                    <label>${requestScope.model.version}</label>
                </td>
            </tr>
            <tr>
                <th>模型简介:</th>
                <td colspan="3"><textarea rows="8" cols="80"
                                          name="modelDescription"
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
                    <%--                    <input type="text" value="${requestScope.model.completionDepartment}" disabled="disabled">--%>
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
                    <%--                    <input type="text" value="${requestScope.model.modelDirectoryCode}" disabled="disabled">--%>
                    <label>${requestScope.model.modelDirectoryCode}</label>
                </td>
            </tr>
            <tr>
                <th>运行环境:</th>
                <td colspan="3"><textarea rows="8" cols="80"
                                          name="runtimeEnvironment"
                                          disabled="disabled">${requestScope.model.runtimeEnvironment}</textarea>
                </td>
            </tr>
            <tr>
                <th>测试说明:</th>
                <td colspan="3"><textarea rows="8" cols="80"
                                          name="testDescription"
                                          disabled="disabled">${requestScope.model.testDescription}</textarea></td>
            </tr>
        </table>
    </fieldset>
    <%--如果用户点击的是申请使用模型--%>
    <c:if test="${requestScope.operationType=='apply'}">
        <fieldset>
            <legend>申请信息</legend>
            <table>
                <tr>
                    <th>
                        申请人:
                    </th>
                    <td>
                            ${sessionScope.user.username}
                    </td>
                </tr>
                <tr>
                    <th>申请缘由:</th>
                    <td colspan="3">
                        <textarea name="applyReason" rows="8" cols="80"></textarea>
                    </td>
                </tr>
            </table>
            <div class="bottom_button">
                <input type="submit" id="apply" value="申请使用"/>
            </div>
        </fieldset>
    </c:if>
    <!-- 如果是模型发布审核 则显示方法的信息、参数的信息并提供测试 -->
    <c:if test="${requestScope.operationType == 'detail' or requestScope.operationType == 'releaseAudit'}">
        <%
            int i = 1;
            request.setAttribute("i", i);
        %>
        <c:forEach items="${requestScope.methods}" var="item">
            <fieldset class="methodInfo">
                <legend>方法包${i}</legend>
                <%
                    int temp = (int) request.getAttribute("i");
                    request.setAttribute("i", temp + 1);
                %>
                <div>
                    基本信息
                </div>
                <table>
                    <!-- 方法信息 -->
                    <tr>
                        <th>方法名称:</th>
                        <td>
                                <%--                            <input type="text" name="methodName" value="${item.key.methodName}"--%>
                                <%--                                   disabled="disabled"/>--%>
                            <label>${item.key.methodName}</label>
                        </td>
                        <th>调用名称:</th>
                        <td>
                                <%--                            <input type="text" name="methodCallName" value="${item.key.methodCallName}"--%>
                                <%--                                   disabled="disabled">--%>
                            <label>${item.key.methodCallName}</label>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            方法类名:
                        </th>
                        <td>
                            <label class="className">${item.key.implementationClass}</label>
                        </td>
                        <th>
                            返回值类型:
                        </th>
                        <td>
                                <%--                            <input name="returnType" value="${requestScope.method.returnType}" disabled="disabled">--%>
                            <label>${item.key.returnType}</label>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            功能描述:
                        </th>
                        <td colspan="3">
                    <textarea name="methodDescription" rows="8"
                              cols="80" disabled="disabled">${item.key.methodDescription}</textarea>
                        </td>
                    </tr>
                </table>
                <!-- 参数信息 -->
                    <%--如果有参数--%>
                <c:if test="${not empty item.value}">
                    <div>参数信息</div>
                    <table class="paramInfo">
                        <tr>
                            <td>
                                参数序号
                            </td>
                            <td>
                                参数名称
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
                        <%
                            int j = 1;
                            request.setAttribute("j", j);
                        %>
                            <%--输出各个参数--%>
                        <c:forEach items="${item.value}" var="it">
                            <tr>
                                <td>
                                    <label>${j}</label>
                                </td>
                                <%
                                    temp = (int) request.getAttribute("j");
                                    request.setAttribute("j", temp + 1);

                                %>
                                <td>
                                        <%--                                    <input type="text" name="paramName"--%>
                                        <%--                                           value="${it.paramName}"--%>
                                        <%--                                           disabled="disabled">--%>
                                    <label>${it.paramName}</label>
                                </td>
                                <td>
                                        <%--                                    <input type="test" name="paramType"--%>
                                        <%--                                           value="${it.paramType}"--%>
                                        <%--                                           disabled="disabled">--%>
                                    <label class="paramType">${it.paramType}</label>
                                </td>
                                    <%--参数示例可以改，管理员在审核的时候可以输入不同的值进行测试--%>
                                <td>
                                    <input type="text" name="paramSample"
                                           class="paramSample" value="${it.paramSample}" disabled="disabled">
                                </td>
                                <td>
                                        <%--                                    <input type="text" name="paramDescription"--%>
                                        <%--                                           value="${it.paramDescription}"--%>
                                        <%--                                           disabled="disabled">--%>
                                    <label>${it.paramDescription}</label>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <table>
                    <div>
                        方法测试
                    </div>
                    <!-- 结果值 -->
                    <tr>
                        <th>预期结果:</th>
                        <td colspan='3'><textarea rows='8' cols='80'
                                                  name='expectedResult'
                                                  disabled="disabled">${item.key.expectedResult}</textarea>
                    </tr>
                    <tr>
                        <th>实际结果:</th>
                        <td colspan='3'><textarea rows='8' cols='80' name='realResult' class="realResult"
                                                  readonly='readonly'></textarea>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <c:if test="${requestScope.operationType=='detail'}">
                            <td></td>
                            <td><input type="button" method="${item.key.methodCallName}" class="test"
                                       value="测试"/><span class="testMsg"></span></td>
                        </c:if>
                        <c:if test="${requestScope.operationType!='detail'}">
                            <td><input type="button" method="${item.key.methodCallName}" class="test"
                                       value="可行性测试"/><span
                                    class="testMsg"></span></td>
                            <td><input type="button" method="${item.key.methodCallName}" class="verify"
                                       value="唯一性验证"/><span
                                    class="verifyMsg"></span></td>
                        </c:if>
                    </tr>
                </table>
            </fieldset>
        </c:forEach>
    </c:if>
    <%--如果是模型申请审核 显示申请信息--%>
    <c:if test="${requestScope.operationType == 'applyAudit'}">
        <fieldset>
            <legend>申请信息</legend>
            <table>
                <tr>
                    <th>
                        申请人:
                    </th>
                    <td>
                            ${requestScope.record.applicantName}
                    </td>
                </tr>
                <tr>
                    <th>
                        申请日期:
                    </td>
                    <td>
                            ${requestScope.record.applyDate}
                    </td>
                </tr>
                <tr>
                    <th>申请缘由:</th>
                    <td colspan="3">
                    <textarea name="applyReason" rows="8" cols="80"
                              disabled="disabled">${requestScope.record.applyReason}</textarea>
                    </td>
                </tr>
            </table>
        </fieldset>
    </c:if>
    <%--审核信息--%>
    <%--如果是模型使用申请审核 或者 模型发布申请审核 则显示审核信息--%>
    <c:if test="${requestScope.operationType=='applyAudit' or requestScope.operationType=='releaseAudit'}">
        <fieldset>
            <legend>审核信息</legend>
            <table>
                <tr>
                    <th>审核意见:</th>
                    <td>
                        <textarea name="reviewOpinion" id="reviewOpinion" rows="8" cols="80"></textarea>
                    </td>
                </tr>
            </table>
            <div class="bottom_button">
                <input type="button" id="refuse" value="拒绝"/>
                <input type="button" id="agree" value="同意"/>
            </div>
        </fieldset>
    </c:if>
</form>
</body>
</html>
