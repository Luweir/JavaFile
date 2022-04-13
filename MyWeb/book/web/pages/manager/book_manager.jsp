<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--    静态包含 base标签、css样式、jQuery--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        // 页面加载后
        $(function () {
            // 给删除的 a 标签绑定单击事件，用于删除的确认
            $("a.deleteClass").click(function () {
                // 在事件的 function 函数中有this 对象，这个this对象是当前正在响应事件的 DOM 对象
                /**
                 * 确认提示框函数，参数是它的提示内容
                 * 两个按钮 确定 和 取消
                 * 返回 true 点击确认，返回false，点击取消
                 */
                return confirm("你确定要删除【 " + $(this).parent().parent().find("td:first").text() + "】？");
                // 如果return false  那么是阻止提交行为==不提交请求
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--静态包含 manager 管理模块的菜单--%>
    <%@include file="/pages/common/manage_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <%--之前在BookServlet 把 page 保存在Request 域中 现在拿出来用  当前页面显示的书为page的items--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a>
                </td>
                <td><a class="deleteClass"
                       href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <%@include file="/pages/common/page_nav.jsp" %>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>