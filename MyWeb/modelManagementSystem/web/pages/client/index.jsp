<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/12
  Time: 13:15
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
    <title>模型管理系统</title>
    <base href=<%=basePath%>>
    <style>
        iframe{
            border:0px;
            margin: 0;
            padding: 0;

        }
    </style>
</head>

<body>
<%--顶部位置--%>
<iframe height="50px" width="100%" src="pages/common/head.jsp" ></iframe>
<%--中间左侧导航栏--%>
<iframe width="10%" height="850px" src="pages/common/navigation.jsp" >

</iframe>
<%--中间右侧显示框--%>
<iframe name="target" width="89%" height="850px" src="modelServlet?action=pageForModelQuery" >

</iframe>
<%--&lt;%&ndash;底部位置&ndash;%&gt;--%>
<%--<iframe height="50px" width="100%" src="pages/common/footer.jsp"></iframe>--%>
</body>
</html>
