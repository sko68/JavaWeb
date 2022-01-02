<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>

    <%-- 静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            //给删除的a标签帮顶单机事件，用于删除的确认操作
            $("a.deleteClass").click(function () {
                //confirm("卧槽");
                //confirm是确认提示框函数
                //参数是它提示的内容
                //返回true表示点击了确认，返回false表示点击了取消
                return confirm("您确定要删除[" + $(this).parent().parent().find("td:first").text() + "]吗？");
            });

            //跳到指定页码
            $(function () {
                $("#searchPageBtn").click(function () {
                    let pageNo = $("#pn_input").val();

                    //JavaScript语言提供了一个location地址栏对象
                    //他有一个属性href，他可以获取浏览器地址栏中的地址
                    //  alert(location.href);

                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;


                });
            });
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>

    <%--静态包含manager管理模块的订单--%>
    <%@include file="/pages/common/manager_menu.jsp" %>

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
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
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

<%@include file="/pages/common/page_nav.jsp"%>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>
