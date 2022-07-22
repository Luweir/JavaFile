<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--    静态包含 base标签、css样式、jQuery--%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--静态包含 登陆 成功之后的菜单--%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 给删除绑定单击事件
            $("a.deleteItem").click(function () {
                return confirm("确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？")
            })
            // 给清空绑定单击事件
            $("a.clearCart").click(function () {
                return confirm("你确定要清空购物车吗")

            })
            // 给输入框绑定失去焦点事件 === onchange 内容发生改变事件
            $(".updateCount").change(function () {
                let name = $(this).parent().parent().find("td:first").text();
                let count = $(this).val();
                let id = $(this).attr("bookId")
                if (confirm("你确定要将【" + name + "】" + "商品数量修改为【" + count + "】吗")) {
                    // 发起请求给服务器修改
                    // basePath 一定要写在字符串里面
                    location.href = "${basePath}cartServlet?action=updateCount&id=" + id + "&count=" + count;
                } else {
                    // defaultValue 是表单项 Dom 对象的属性，表示默认value属性值
                    this.value = this.defaultValue;
                }

            })

        })
    </script>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <%--如果购物车为空--%>
            <tr>
                <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快去浏览商品吧！！！</a></td>
            </tr>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <%--如果购物车不为空--%>
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td><input class="updateCount" style="width:40px" bookId="${entry.value.id}" type="text"
                               value="${entry.value.count}"></td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a href="cartServlet?action=deleteItem&id=${entry.value.id}" class="deleteItem">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>

    </table>

    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
                <%--注意 获取totalCount的值 是调用cart 的 getTotalCount方法，而不是访问属性--%>
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a href="cartServlet?action=clear" class="clearCart">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>


</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>