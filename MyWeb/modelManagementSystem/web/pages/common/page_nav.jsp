<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/7
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条的开始--%>
<div id="page_nav" style="display: flex;justify-content: center">
    <%--         页数 大于 1才显示首页和上一页--%>
    <%--        注意 JSTL ${} 里面是放表达式的--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出开始--%>
    <c:choose>
        <%--情况一，总页码小于5页的情况--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%--情况二：总页码大于5页的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1，当当前页为1,2,3时--%>
                <c:when test="${requestScope.page.pageNo <=3 }">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--小情况2，当当前页为尾三页时--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3 }">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <%--小情况3：当处于4  到 总页数-3 之间时 ，正常输出5个页码--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}"
               var="i">
        <c:if test="${ i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${ i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出结束--%>

    <%--页数小于 总页数 才显示下一页和末页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn"
             id="pn_input" style="width: 50px"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javaScript">
        $(function () {
            // 调到指定的页码
            $("#searchPageBtn").click(function () {
                let pageNo = $("#pn_input").val();
                // 越界判断
                if (pageNo < 1 || pageNo >${requestScope.page.pageTotal}) {
                    return false;
                }
                // javaScript 提供了一个 location 地址栏对象
                // 它有一个属性 href 可以获取和设置 地址栏的地址
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;

            })
        })

    </script>
</div>
<%--分页条结束--%>