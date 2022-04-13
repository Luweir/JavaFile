<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/11
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<%--<base href=<%=basePath%>>--%>
<%--直接跳主页--%>
<%--<jsp:forward page="/pages/client/index.jsp"></jsp:forward>--%>

<html>
<title>Title</title>
<base href=<%=basePath%>>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script>
    $(document).ready(function () {
        location.href = "${basePath}/pages/client/index.jsp";
    })
</script>
</html>