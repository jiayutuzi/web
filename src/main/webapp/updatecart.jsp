<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.bean.Cart"%>
<%@ page import="com.dao.CartDao"%>
<html>
<head>
    <title>修改界面</title>
    <style type="text/css">
        body{
            background-repeat: no-repeat;
            background-position: center;
        }

    </style>
</head>
<%
    Cart cart=(Cart)request.getAttribute("gotoupdate");
%>
<body>
<div style="text-align:center;margin-top: 120px">
    <form action="CartServlet?action=update" method="post">
        <table style="margin-left:40%">
            <caption>修改商品信息</caption>
             <tr>
                <td> 购买数量：</td>
                <td><input name="number" type="text" size="20" value="<%=cart.Getnumber() %>"></td>
                <input type="hidden" name="id" value="<%=cart.Getid()%>">
            </tr>
        </table>
        <input type="submit" value="修改">
        <input type="reset" value="取消" onclick="history.go(-1)">
    </form>
    <br>
    <a href="CartServlet?action=mycart">返回</a>
    </form>
</div>

</body>
</html>
