<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:55
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
    <link rel="stylesheet" type="text/css" href="static/css/css.css" />
    <script>
        $(function () {
            // Я����¼ID��ת����¼����ҳ��
            $(".recordDetail").click(function () {
                let recordId = $(this).attr("recordId");
                location.href = "${basePath}recordServlet?action=recordDetail&recordId=" + recordId;
            })
        })
    </script>

</head>
<body>
<div id="main">
    <div class="top_search_bar">

    </div>
    <table>
        <tr>
            <th>
                ģ������
            </th>
            <th>
                ҵ������
            </th>
            <th>
                ������
            </th>
            <th>
                ����ʱ��
            </th>
            <th>
                ״̬
            </th>
            <th>
                ����
            </th>
        </tr>
        <c:if test="${not empty requestScope.records}">
            <c:forEach items="${requestScope.records}" var="entry">
                <tr>
                    <td>${entry.modelName}</td>
                    <c:if test="${entry.recordType=='apply'}">
                        <td>
                            ʹ������
                        </td>
                    </c:if>
                    <c:if test="${entry.recordType=='release'}">
                        <td>
                            ��������
                        </td>
                    </c:if>
                    <td>${entry.applicantName}</td>
                    <td>${entry.applyDate}</td>
                    <td>${entry.recordState}</td>
                    <td>
                        <input type="button" class="recordDetail" recordId="${entry.id}" value="�鿴">
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${ empty requestScope.records}">
            <tr>
                <td colspan="6">
                    ��δ��ѯ������Ѱ�ҵ��
                </td>
            </tr>
        </c:if>
    </table>
</div>
</body>
</html>
